import java.io.*;
import java.net.*;
import java.util.HashMap;

public class server {
    // Define a port number for the server 
    private static final int PORT = 5927; 
    // A HashMap to store jokes with their corresponding numbers
    private static HashMap<Integer, String> jokes = new HashMap<>();

    public static void main(String[] args) {
        // Populate the HashMap with jokes
        jokes.put(1, "Why do programmers prefer dark mode? Because light attracts bugs.");
        jokes.put(2, "How many programmers does it take to change a light bulb? None, that's a hardware problem.");
        jokes.put(3, "Why do Java developers wear glasses? Because they can't C#.");

        // Try to create a server socket listening on the specified port
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            // Continuously listen for client connections
            while (true) {
                Socket socket = serverSocket.accept(); // Accept an incoming connection
                System.out.println("New client connected");

                // Create and start a new thread for each connected client
                new ServerThread(socket).start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Define a ServerThread class to handle client communication
    private static class ServerThread extends Thread {
        private Socket socket; // Socket to communicate with the client

        public ServerThread(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            // Set up input and output streams for socket communication
            try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

                String text;
                // Read input from client and process commands
                while ((text = input.readLine()) != null) {
                    if (text.equalsIgnoreCase("bye")) { // Check if the command is "bye"
                        output.println("disconnected"); // Send goodbye message
                        break; // Exit the loop and end the connection
                    }

                    // Process joke requests
                    try {
                        int jokeNumber = Integer.parseInt(text); // Parse the joke number
                        String joke = jokes.getOrDefault(jokeNumber, "Joke not found."); // Retrieve the joke
                        output.println(joke); // Send the joke to the client
                    } catch (NumberFormatException e) {
                        // Handle invalid input (non-integer)
                        output.println("Invalid input. Please send a joke number or 'bye' to exit.");
                    }
                }

                socket.close(); // Close the client socket connection
                System.out.println("disconnected");
            } catch (IOException ex) {
                System.out.println("Server exception: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}

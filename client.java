import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {
    private static final String SERVER_ADDRESS = "localhost"; // Server address
    private static final int SERVER_PORT = 5927; // Server port, matching the server code port

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to the joke server. Enter a joke number (1-3) or type 'bye' to exit.");
            
            while (true) {
                System.out.print("Enter command: ");
                String userInput = scanner.nextLine(); // Read user input

                output.println(userInput); // Send user input to server

                if ("bye".equalsIgnoreCase(userInput)) {
                    String response = input.readLine(); // Ensure to read the final server response
                    if ("disconnected".equalsIgnoreCase(response)) {
                        System.out.println("exit");
                    }
                    break;
                }

                String response = input.readLine();
                System.out.println("Server response: " + response);
            }

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}

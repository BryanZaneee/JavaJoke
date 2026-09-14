# JavaJoke

A small Java client and server that hand out jokes over a TCP socket. The
server listens on port 5927 and spawns a thread per connection; the client
sends a joke number and prints what comes back.

Written for a networking course, to practice socket I/O and multi-threading.

## Installation

Requires a JDK. No build tool, no dependencies.

```bash
git clone https://github.com/BryanZaneee/JavaJoke.git
cd JavaJoke
javac server.java client.java
```

## Usage

Start the server in one terminal:

```bash
java server
```

Run the client in another:

```bash
java client
```

The client connects to `localhost:5927`. Enter `1`, `2`, or `3` to get the
joke from the matching `joke*.txt` file, or `bye` to disconnect. Both the
address and the port are compiled into `client.java`, so point it elsewhere
by editing `SERVER_ADDRESS` and recompiling.

## Contributing

This is archived coursework and is not taking contributions.

## License

No license file is included, so this is "all rights reserved" by default.

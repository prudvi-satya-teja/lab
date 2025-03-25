import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.net.InetSocketAddress;
import java.util.Iterator;

public class ConcurrentUpperCaseServer {
    public static void main(String[] args) {
        int port = 5000;

        try {
            // Create a selector to handle multiple channels
            Selector selector = Selector.open();

            // Open a server socket channel
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false); // Set to non-blocking mode
            serverChannel.bind(new InetSocketAddress(port));

            // Register the server channel with the selector for accepting connections
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server started on port " + port);

            while (true) {
                // Wait for events (blocking until events are available)
                selector.select();

                // Get the selected keys (ready channels)
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();

                    // Remove the key to avoid reprocessing
                    keyIterator.remove();

                    // Check if it's a new connection
                    if (key.isAcceptable()) {
                        SocketChannel clientChannel = serverChannel.accept();
                        clientChannel.configureBlocking(false);

                        // Register the client channel for reading
                        clientChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("New client connected: " + clientChannel.getRemoteAddress());

                    } else if (key.isReadable()) {
                        // Handle data from a client
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);

                        try {
                            int bytesRead = clientChannel.read(buffer);
                            if (bytesRead == -1) {
                                // Client disconnected
                                clientChannel.close();
                                System.out.println("Client disconnected.");
                                continue;
                            }

                            // Process the data
                            buffer.flip();
                            String input = new String(buffer.array(), 0, bytesRead);
                            System.out.println("Received: " + input.trim());

                            // Convert to uppercase
                            String response = input.trim().toUpperCase();

                            // Send response back to client
                            buffer.clear();
                            buffer.put(response.getBytes());
                            buffer.flip();
                            clientChannel.write(buffer);
                        } catch (IOException e) {
                            // Handle client disconnection
                            clientChannel.close();
                            System.out.println("Client forcibly closed the connection.");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

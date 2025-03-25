import java.io.*;
import java.net.*;

public class ReverseServer {
    public static void main(String[] args) {
        int port = 1234; // Define the port number for the server
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running and waiting for client connection...");

            while (true) {
                // Accept client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected!");

                // Create input and output streams
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read input from client
                String input = in.readLine();
                System.out.println("Received from client: " + input);

                // Reverse the input string
                String reversed = new StringBuilder(input).reverse().toString();

                // Send reversed string back to the client
                out.println(reversed);
                System.out.println("Sent to client: " + reversed);

                // Close the client connection
                clientSocket.close();
                System.out.println("Client connection closed.\n");
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

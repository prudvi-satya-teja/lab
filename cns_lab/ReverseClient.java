import java.io.*;
import java.net.*;

public class ReverseClient {
    public static void main(String[] args) {
        String hostname = "localhost"; // Server hostname
        int port = 1234; // Server port number

        try (Socket socket = new Socket(hostname, port)) {
            // Create input and output streams
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Get user input
            System.out.print("Enter a sentence to reverse: ");
            String sentence = userInput.readLine();

            // Send input to server
            out.println(sentence);

            // Receive reversed string from server
            String reversed = in.readLine();
            System.out.println("Reversed sentence from server: " + reversed);

        } catch (IOException e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

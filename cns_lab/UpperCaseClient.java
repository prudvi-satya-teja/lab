import java.io.*;
import java.net.*;

public class UpperCaseClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to the server. Type your text (type 'exit' to quit):");

            String input;
            while ((input = userInput.readLine()) != null) {
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                // Send to server
                out.println(input);

                // Read response from server
                String response = in.readLine();
                System.out.println("Server response: " + response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

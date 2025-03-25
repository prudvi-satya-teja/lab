import java.io.*;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        String serverIP = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(serverIP, port);
             BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to server. Type a message to send:");
            
            while (true) {
                String userMessage = input.readLine();
                if (userMessage.equalsIgnoreCase("exit")) break;
                
                out.println(userMessage);
                System.out.println("Server response: " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

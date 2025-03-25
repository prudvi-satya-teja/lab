import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        int port = 5000; // Port number
        String filePath = "F:/CSE Department/2024-25/II Sem/CNS Lab/Week 07/attendance.docx"; // File to be sent

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running and waiting for a connection...");

            // Accept client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            // File transfer logic
            try (FileInputStream fileInputStream = new FileInputStream(filePath);
                 BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                 OutputStream outputStream = clientSocket.getOutputStream()) {

                byte[] buffer = new byte[4096];
                int bytesRead;

                System.out.println("Sending file...");
                while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                System.out.println("File sent successfully.");
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

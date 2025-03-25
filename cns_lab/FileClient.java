import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Server IP
        int port = 5000; // Server port
        String saveFilePath = "F:/CSE Department/2024-25/II Sem/CNS Lab/received_file.docx"; // File path to save the received file

        try (Socket socket = new Socket(serverAddress, port);
             InputStream inputStream = socket.getInputStream();
             FileOutputStream fileOutputStream = new FileOutputStream(saveFilePath);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            System.out.println("Receiving file...");
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File received and saved to: " + saveFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

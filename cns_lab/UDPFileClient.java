import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPFileClient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the file path to send: ");
            String filePath = scanner.nextLine();
            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("File does not exist.");
                return;
            }

            // Send file name to server
            byte[] fileNameBytes = file.getName().getBytes();
            DatagramPacket namePacket = new DatagramPacket(fileNameBytes, fileNameBytes.length, serverAddress, serverPort);
            socket.send(namePacket);

            // Read file and send in chunks
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    DatagramPacket filePacket = new DatagramPacket(buffer, bytesRead, serverAddress, serverPort);
                    socket.send(filePacket);
                }
            }

            // Send end signal
            byte[] endSignal = "EOF".getBytes();
            DatagramPacket endPacket = new DatagramPacket(endSignal, endSignal.length, serverAddress, serverPort);
            socket.send(endPacket);

            System.out.println("File sent successfully.");
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


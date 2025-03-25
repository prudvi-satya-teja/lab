import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPFileServer {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9876);
            System.out.println("Server is running and waiting for file...");

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // Receive file name
            socket.receive(packet);
            String fileName = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Receiving file: " + fileName);

            try (FileOutputStream fos = new FileOutputStream("Received_" + fileName)) {
                while (true) {
                    // Receive file data
                    socket.receive(packet);
                    String endSignal = new String(packet.getData(), 0, packet.getLength());

                    // Break if the end signal is received
                    if (endSignal.equals("EOF")) {
                        System.out.println("File transfer complete.");
                        break;
                    }

                    // Write data to file
                    fos.write(packet.getData(), 0, packet.getLength());
                }
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

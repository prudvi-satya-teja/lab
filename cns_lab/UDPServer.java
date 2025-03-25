import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);
            System.out.println("Server is running...");

            byte[] receiveBuffer = new byte[1024];
            byte[] sendBuffer;

            while (true) {
                // Receive packet
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);

                String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from client: " + sentence);

                // Reverse the sentence
                String reversedSentence = new StringBuilder(sentence).reverse().toString();

                // Send reversed sentence back
                sendBuffer = reversedSentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length,
                        receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);
                System.out.println("Reversed sentence sent to client: " + reversedSentence);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

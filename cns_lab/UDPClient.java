import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            byte[] sendBuffer;
            byte[] receiveBuffer = new byte[1024];

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a sentence to reverse (or type 'exit' to quit):");

            while (true) {
                // Get user input
                String sentence = scanner.nextLine();
                if (sentence.equalsIgnoreCase("exit")) {
                    System.out.println("Client exiting...");
                    break;
                }

                // Send sentence to server
                sendBuffer = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
                clientSocket.send(sendPacket);

                // Receive reversed sentence from server
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                clientSocket.receive(receivePacket);

                String reversedSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Reversed sentence from server: " + reversedSentence);
            }

            scanner.close();
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

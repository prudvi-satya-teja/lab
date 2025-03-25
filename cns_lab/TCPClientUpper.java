import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class TCPClientUpper {
    public static void main(String[] args) throws IOException {
        SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", 5000));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter text (or 'exit' to quit): ");
            String msg = scanner.nextLine();
            if (msg.equalsIgnoreCase("exit")) break;
            buffer.clear();
            buffer.put(msg.getBytes());
            buffer.flip();
            client.write(buffer);

            buffer.clear();
            client.read(buffer);
            buffer.flip();
            System.out.println("Server: " + new String(buffer.array(), 0, buffer.limit()));
        }
        client.close();
        scanner.close();
    }
}

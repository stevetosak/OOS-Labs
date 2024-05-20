import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.Random;

public class UdpClient extends Thread {
    private final int port;
    private final String serverName;

    String [] messages = new String[]{"login","logout","Zdravo","Resen","Cao"};

    public UdpClient(int port, String serverName) {
        this.port = port;
        this.serverName = serverName;
    }

    @Override
    public void run() {
        while (true) {
            byte[] buffer;
            try (DatagramSocket socket = new DatagramSocket()){
                InetAddress addr = InetAddress.getByName(serverName);
                Random rnd = new Random();
                int idx = rnd.nextInt(messages.length);
                String msg = messages[idx];
                if (msg.equals("logout")) break;
                buffer = msg.getBytes();

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, addr, port);
                socket.send(packet);
                buffer = new byte[128];
                packet = new DatagramPacket(buffer, buffer.length, addr, port);
                socket.receive(packet);

            } catch (RuntimeException | IOException e) {
                throw new RuntimeException(e);
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        UdpClient client = new UdpClient(6633,"localhost");
        client.start();
    }
}

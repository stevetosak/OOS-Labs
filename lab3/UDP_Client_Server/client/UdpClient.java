import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.Random;

public class UdpClient extends Thread {
    private final int port;
    private final String serverName;
    private boolean isLoggedIn = false;

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
                Random rnd = new Random();
                int idx = rnd.nextInt(messages.length);
                String msg = messages[idx];

                buffer = msg.getBytes();
                InetAddress addr = InetAddress.getByName(serverName);
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, addr, port);
                socket.send(packet);

                buffer = new byte[128];
                packet = new DatagramPacket(buffer, buffer.length, addr, port);
                socket.receive(packet);
                String receivedMessage = new String(packet.getData(),0, packet.getLength());
                System.out.println(receivedMessage);
                if (msg.equals("logout")) break;

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
        UdpClient client = new UdpClient(6633,args[0]);
        client.start();
    }
}

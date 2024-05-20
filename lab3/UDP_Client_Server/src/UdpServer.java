import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

public class UdpServer extends Thread {
    private final DatagramSocket socket;
    private String prevMsg;
    public UdpServer(int portNum) throws SocketException {
        this.socket = new DatagramSocket(portNum);
    }

    @Override
    public void run() {
        System.out.println("Server initialized.");
        while (true){
            byte[] buffer = new byte[128];
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(p);
                String message = new String(p.getData(),0,p.getLength());
                buffer = message.getBytes();

                if(message.equals("login")) {
                    System.out.println("Logged in.");
                    prevMsg = "login";
                }
                else if (message.equals("logout")) {
                    System.out.println("Logged out.");
                }
                else System.out.println("Server - Received message: " + message);

                p = new DatagramPacket(buffer,buffer.length,p.getAddress(),p.getPort());

                socket.send(p);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static void main(String[] args) throws SocketException {
        UdpServer srv = new UdpServer(6633);
        srv.start();
    }
}

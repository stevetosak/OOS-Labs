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

                if(message.equals("login")) {
                    message = "Logged in";
                }
                else if (message.equals("logout")) {
                    message = "Logged out";
                }
                else System.out.println("Server - Received message: " + message);
                buffer = message.getBytes();
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

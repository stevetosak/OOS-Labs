import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        int port = 9090;
        InetAddress addr = InetAddress.getLocalHost();
        TcpServer server = new TcpServer(9090);
        server.start();

        for(int i = 0; i < 5; i++){
            TcpClient client = new TcpClient(port,addr,"USER" +i);
            client.start();
        }

    }
}
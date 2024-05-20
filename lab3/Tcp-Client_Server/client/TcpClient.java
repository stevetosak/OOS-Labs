import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class TcpClient extends Thread {
    private int serverPort;
    private InetAddress serverAddress;
    private int sentMessagesCount = 0;
    String[] messages = new String[]{"logout", "selo veselo", "RESEEEN", "Type O negative", "Pearl Jam"};

    public TcpClient(int serverPort, InetAddress serverAddress) {
        this.serverPort = serverPort;
        this.serverAddress = serverAddress;
    }

    String getRandomMessage() {
        Random rnd = new Random();
        if (rnd.nextDouble() < 0.05) {
            return messages[0];
        } else {
            int idx = 1 + rnd.nextInt(messages.length - 1);
            return messages[idx];
        }
    }

    @Override
    public void run() {
        Socket socket = null;
        try {
            socket = new Socket(serverAddress, serverPort);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            if (new Random().nextDouble() < 0.95)
                out.write("login");
            else
                out.write("Salatiranje brate");

            out.newLine();
            out.flush();

            String line = in.readLine();

            while (line != null && !line.equals("CLOSE")) {
                System.out.println( " --> " + "Received message from server: " + line);
                out.write(getRandomMessage());
                out.newLine();
                out.flush();
                sentMessagesCount++;
                line = in.readLine();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            socket.close();
        } catch (IOException e ) {
            throw  new RuntimeException();
        }

        System.out.println("Thread with  " + " ID: " + getId() + " done.");
        System.out.println("Number of messages sent = " + sentMessagesCount);

    }

    public static void main(String[] args) throws UnknownHostException {
        TcpClient client = new TcpClient(9090, InetAddress.getByName(args[0]));
        client.start();
    }
}

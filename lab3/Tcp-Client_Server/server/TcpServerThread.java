import java.io.*;
import java.net.Socket;

public class TcpServerThread extends Thread {
    private Socket socket;
    private TcpServer server;
    private String msg;

    public TcpServerThread(Socket socket,TcpServer server) {
        this.socket = socket;
        this.server = server;
    }

    void showReceivedMessage(){
        System.out.println("Received message: " + msg);
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.msg = reader.readLine();
            showReceivedMessage();
            if (msg.equals("login")) {
                writer.write(socket.getInetAddress() + ":" + socket.getPort() + " -- Logged in\n");
                writer.flush();
                server.incrementMessages();

                while (true) {
                    msg = reader.readLine();
                    if (msg == null) throw new InterruptedException("Message is null. - Client thread was probably interrupted");
                    showReceivedMessage();
                    if (msg.equals("logout")) break;

                    writer.write(msg + "\n");
                    writer.flush();

                    server.incrementMessages();
                }
                writer.write("Logged out.\n");
            } else {
                writer.write("*** ERROR: First message must be a login message ***\n");
            }

            writer.write("CLOSE\n");
            writer.flush();

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            server.incrementMessages();
            System.out.println("Closing connection: " + socket.getInetAddress() + " Port: " + socket.getPort());
            try {
                this.socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            server.showCounter();
        }
    }

}

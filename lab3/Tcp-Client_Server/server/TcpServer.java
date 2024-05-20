import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer extends Thread {
    private final int port;
    private int totalMessagesCount;

    public TcpServer(int port) {
        this.port = port;
    }

    public synchronized void incrementMessages(){
        totalMessagesCount++;
    }

    public void showCounter(){
        System.out.println("Total number of messages: " + totalMessagesCount);
    }

    @Override
    public void run() {
        System.out.println("Server initializing...");
        ServerSocket serverSocket = null;
        try {
           serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Server is initialized.");

        while (true){
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                TcpServerThread thread = new TcpServerThread(clientSocket,this);
                thread.start();
                System.out.println("Connection: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " established.");
                showCounter();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        TcpServer server = new TcpServer(9090);
        server.start();
    }
}

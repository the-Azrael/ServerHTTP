import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server extends Thread {
    private final int port;
    private final String path;
    private final ServerSocket serverSocket;

    public Server(int port, String path) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.path = path;
        this.start();
    }

    @Override
    public synchronized void start() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(64);
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                Client client = new Client(socket, path);
                executor.submit(client);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

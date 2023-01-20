import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server extends Thread {
    private final int port;
    private final ServerSocket serverSocket;
    private final FileList fileList;

    public Server(int port, String path) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.fileList = new FileList(path);
        this.start();
    }

    @Override
    public synchronized void start() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(64);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket, fileList);
                executor.execute(serverThread);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

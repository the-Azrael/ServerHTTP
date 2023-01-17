import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client extends Thread {
    private final Socket socket;
    private final BufferedReader in;
    private final BufferedOutputStream out;
    private final FileList fileList;

    public Client(Socket socket, String path) throws IOException {
        this.socket = socket;
        this.fileList = new FileList(path);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new BufferedOutputStream(socket.getOutputStream());
    }

    @Override
    public synchronized void start() {
        super.start();
    }
}



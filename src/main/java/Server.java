import java.net.ServerSocket;

public class Server {
    private final String path;
    private final FileList fileList;
    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket, String path) {
        this.path = path;
        this.serverSocket = serverSocket;
        this.fileList = new FileList(path);
    }

    public FileList getFileList() {
        return fileList;
    }
}

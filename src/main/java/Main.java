import java.io.IOException;
import java.net.ServerSocket;

public class Main {
  private static final int port = 9999;
  public static void main(String[] args) throws IOException {
//    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(64);
    ServerSocket socket = new ServerSocket(port);
    Server server = new Server(socket, "./public/");
    System.out.println();
    System.out.println(server.getFileList().getListOfFiles().toString());
  }
}



import java.io.IOException;

public class Main {
  private static final int port = 9999;
  private static final String DEFAULT_FILE_NAME = "index.html";
  public static void main(String[] args) throws IOException {
    Server server = new Server(port, "./public");
  }
}



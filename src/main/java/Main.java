import java.io.IOException;

public class Main {
  private static final int port = 9999;
  public static void main(String[] args) throws IOException {
    Server server = new Server(port, "./public/");
  }
}



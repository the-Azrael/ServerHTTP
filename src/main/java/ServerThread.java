import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerThread implements Runnable {
    private static final AtomicInteger cnt = new AtomicInteger(0);
    private final int id;
    private final Socket socket;
    private final BufferedReader in;
    private final BufferedOutputStream out;
    private final FileList fileList;


    public ServerThread(Socket socket, FileList fileList) throws IOException {
        id = cnt.incrementAndGet();
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new BufferedOutputStream(socket.getOutputStream());
        this.fileList = fileList;
    }

    public Request getRequest() {
        Request request = null;
        try {
            if (in.ready()) {
                String inData = in.readLine();
                if (inData != null) {
                    request = new Request(inData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    public Response getResponse(Request request) {
        Response response = new Response(request, fileList, out);
        return response;
    }

    @Override
    public void run() {
        System.out.println("ServerThread " + id + " started!");
        Request request = null;
        Response response = null;
        while(true) {
            request = getRequest();
            if (request != null && request.getSplitData().length == 3) {
                response = getResponse(request);
                if (response != null) {
                    response.write();
                }
                break;
            }
        }
        System.out.println("ServerThread " + id + " ended!");
    }

}



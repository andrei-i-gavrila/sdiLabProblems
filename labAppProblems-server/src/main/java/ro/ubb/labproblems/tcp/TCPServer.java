package ro.ubb.labproblems.tcp;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {

    public static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    private final ExecutorService executorService;
    private int serverPort;
    private Router router;

    public TCPServer(Router router, int serverPort) {
        this.router = router;
        this.serverPort = serverPort;
        this.executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    }


    public void run() {
        try (ServerSocket server = new ServerSocket(serverPort)) {
            System.out.println("Server started... waiting for clients");

            while (true) {
                executorService.submit(new ClientHandler(server.accept(), router));
                System.out.println("New Client accepted");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class ClientHandler implements Runnable {

    private Socket client;
    private Router router;
    private ObjectMapper mapper;

    public ClientHandler(Socket client, Router router) {
        this.client = client;
        this.router = router;
        this.mapper = new ObjectMapper()
                .configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false)
                .configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
    }

    @Override
    public void run() {
        try (OutputStream ostream = client.getOutputStream();
             InputStream istream = client.getInputStream()) {

            Request request = mapper.readValue(istream, Request.class);

            try {
                Response response = new Response(true, router.route(request));
                mapper.writeValue(ostream, response);
            } catch (Exception e) {
                e.printStackTrace();
                mapper.writeValue(ostream, new Response(false, e.getMessage()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

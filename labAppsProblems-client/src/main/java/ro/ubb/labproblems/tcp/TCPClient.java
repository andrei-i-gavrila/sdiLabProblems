package ro.ubb.labproblems.tcp;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {


    private String serverHost;
    private int serverPort;
    private ObjectMapper objectMapper;

    public TCPClient(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        objectMapper = new ObjectMapper()
                .configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false)
                .configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
    }


    public Response sendThenReceive(Request request) {
        try (Socket client = new Socket(serverHost, serverPort);
             OutputStream ostream = client.getOutputStream();
             InputStream istream = client.getInputStream()) {

            objectMapper.writeValue(ostream, request);

            return objectMapper.readValue(istream, Response.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Response(false, e.getMessage());
        }
    }

}

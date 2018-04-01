package ro.ubb.labproblems.tcp;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


//TODO delete after implementing the rmi

@Component
public class TCPClient {


    private String serverHost;
    private int serverPort;
    private ObjectMapper objectMapper;

    public TCPClient(@Value("${server.host}") String serverHost, @Value("${server.port}") int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        objectMapper = new ObjectMapper()
                .configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false)
                .configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
    }


    public Response sendThenReceive(Request request) {
        try (Socket client = new Socket(serverHost, serverPort);
             OutputStream outputStream = client.getOutputStream();
             InputStream inputStream = client.getInputStream()) {

            objectMapper.writeValue(outputStream, request);

            return objectMapper.readValue(inputStream, Response.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Response(false, e.getMessage());
        }
    }

}

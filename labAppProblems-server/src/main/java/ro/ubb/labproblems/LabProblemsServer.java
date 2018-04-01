package ro.ubb.labproblems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ro.ubb.labproblems.tcp.TCPServer;

/**
 * Public class containing the main function
 */
@SpringBootApplication
public class LabProblemsServer {

    public static void main(String... args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(LabProblemsServer.class, args);
        TCPServer server = (TCPServer) applicationContext.getBean("TCPServer");
        server.run();
    }
}

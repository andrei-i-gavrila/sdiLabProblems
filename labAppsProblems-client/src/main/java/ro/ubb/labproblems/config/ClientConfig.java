package ro.ubb.labproblems.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.ubb.labproblems.service.StudentService;

@Configuration
public class ClientConfig {

    @Bean
    RmiProxyFactoryBean studentService(@Value("${server.host}") String host, @Value("${server.port}") String port) {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://" + host + ":" + port + "/" + StudentService.class.getSimpleName());
        rmiProxyFactory.setServiceInterface(StudentService.class);

        return rmiProxyFactory;
    }

    //TODO: add problems and assignments
}

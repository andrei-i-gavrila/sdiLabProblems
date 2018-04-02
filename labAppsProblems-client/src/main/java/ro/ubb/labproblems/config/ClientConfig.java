package ro.ubb.labproblems.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.ubb.labproblems.service.AssignmentService;
import ro.ubb.labproblems.service.ProblemService;
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

    @Bean
    RmiProxyFactoryBean problemService(@Value("${server.host}") String host, @Value("${server.port}") String port) {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://" + host + ":" + port + "/" + ProblemService.class.getSimpleName());
        rmiProxyFactory.setServiceInterface(ProblemService.class);

        return rmiProxyFactory;
    }

    @Bean
    RmiProxyFactoryBean assignmentService(@Value("${server.host}") String host, @Value("${server.port}") String port) {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://" + host + ":" + port + "/" + AssignmentService.class.getSimpleName());
        rmiProxyFactory.setServiceInterface(AssignmentService.class);

        return rmiProxyFactory;
    }
}

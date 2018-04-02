package ro.ubb.labproblems.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.labproblems.domain.entities.Assignment;
import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.domain.validators.AssignmentValidator;
import ro.ubb.labproblems.domain.validators.ProblemValidator;
import ro.ubb.labproblems.domain.validators.StudentValidator;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.repository.sql.AssignmentSqlHandler;
import ro.ubb.labproblems.repository.sql.DatabaseRepository;
import ro.ubb.labproblems.repository.sql.ProblemSqlHandler;
import ro.ubb.labproblems.repository.sql.StudentSqlHandler;
import ro.ubb.labproblems.service.AssignmentService;
import ro.ubb.labproblems.service.ProblemService;
import ro.ubb.labproblems.service.StudentService;

import javax.sql.DataSource;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Configuration
public class ServerConfiguration {

    @Bean
    public Repository<String, Student> studentRepository(StudentValidator studentValidator, StudentSqlHandler studentSqlHandler, JdbcOperations jdbcOperations) {
        return new DatabaseRepository<>(studentValidator, studentSqlHandler, Student.class, jdbcOperations);
    }

    @Bean
    public Repository<String, Problem> problemRepository(ProblemValidator problemValidator, ProblemSqlHandler problemSqlHandler, JdbcOperations jdbcOperations) {
        return new DatabaseRepository<>(problemValidator, problemSqlHandler, Problem.class, jdbcOperations);
    }

    @Bean
    public Repository<String, Assignment> assignmentRepository(AssignmentValidator assignmentValidator, AssignmentSqlHandler assignmentSqlHandler, JdbcOperations jdbcOperations) {
        return new DatabaseRepository<>(assignmentValidator, assignmentSqlHandler, Assignment.class, jdbcOperations);
    }

    @Bean
    public Registry registry(@Value("${server.port}") int port) throws RemoteException {
        return LocateRegistry.createRegistry(port);
    }

    @Bean
    public RmiServiceExporter studentServiceExporter(Registry registry, StudentService studentService) {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(StudentService.class);
        exporter.setServiceName(StudentService.class.getSimpleName());
        exporter.setService(studentService);
        exporter.setRegistry(registry);

        return exporter;
    }

    @Bean
    public RmiServiceExporter problemServiceExporter(Registry registry, ProblemService problemService) {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(ProblemService.class);
        exporter.setServiceName(ProblemService.class.getSimpleName());
        exporter.setService(problemService);
        exporter.setRegistry(registry);

        return  exporter;
    }

    @Bean
    public  RmiServiceExporter assignmentServiceExporter(Registry registry, AssignmentService assignmentService) {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(AssignmentService.class);
        exporter.setServiceName(AssignmentService.class.getSimpleName());
        exporter.setService(assignmentService);
        exporter.setRegistry(registry);

        return exporter;
    }

    @Bean
    DataSource dataSource(@Value("${database.url}") String databaseUrl, @Value("${database.username}") String username, @Value("${database.password}") String password) {

        return DataSourceBuilder.create()
                .url(databaseUrl)
                .username(username)
                .password(password)
                .build();
    }


    @Bean
    JdbcOperations jdbcOperations(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}

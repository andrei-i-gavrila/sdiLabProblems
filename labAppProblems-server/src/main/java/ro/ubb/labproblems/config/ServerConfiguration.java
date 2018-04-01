package ro.ubb.labproblems.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import ro.ubb.labproblems.tcp.Router;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ServerConfiguration {

    @Bean
    public Repository<String, Student> studentRepository(StudentValidator studentValidator, StudentSqlHandler studentSqlHandler, Connection connection) {
        return new DatabaseRepository<>(studentValidator, connection, studentSqlHandler, Student.class);
    }

    @Bean
    public Repository<String, Problem> problemRepository(ProblemValidator problemValidator, ProblemSqlHandler problemSqlHandler, Connection connection) {
        return new DatabaseRepository<>(problemValidator, connection, problemSqlHandler, Problem.class);
    }

    @Bean
    public Repository<String, Assignment> assignmentRepository(AssignmentValidator assignmentValidator, AssignmentSqlHandler assignmentSqlHandler, Connection connection) {
        return new DatabaseRepository<>(assignmentValidator, connection, assignmentSqlHandler, Assignment.class);
    }

    @Bean
    public Connection connection(
            @Value("${database.url}") String databaseUrl,
            @Value("${database.username}") String username,
            @Value("${database.password}") String password) throws SQLException {

        return DriverManager.getConnection(databaseUrl, username, password);
    }

    @Bean
    public Router router(StudentService studentService, ProblemService problemService, AssignmentService assignmentService) {
        return new Router(studentService, problemService, assignmentService);
    }

}

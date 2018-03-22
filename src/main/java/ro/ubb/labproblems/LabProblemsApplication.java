package ro.ubb.labproblems;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import ro.ubb.labproblems.controller.AssignmentController;
import ro.ubb.labproblems.controller.ProblemController;
import ro.ubb.labproblems.controller.StudentController;
import ro.ubb.labproblems.domain.entities.Assignment;
import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.domain.validators.AssignmentValidator;
import ro.ubb.labproblems.domain.validators.ProblemValidator;
import ro.ubb.labproblems.domain.validators.StudentValidator;
import ro.ubb.labproblems.repository.*;
import ro.ubb.labproblems.repository.sql.AssignmentSqlHandler;
import ro.ubb.labproblems.ui.MainMenu;

import java.sql.*;

/**
 * Public class containing the main function
 */
public class LabProblemsApplication {

    public static void main(String... args) {

        String url = "jdbc:postgresql://horton.elephantsql.com:5432/bnehzrpc";
        String username = "bnehzrpc";
        String password = "OVzZ7JX0ulT4ymExjajVerTLDhOOGlVD";
        Connection db;

        try {
            db = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }


        Repository<String, Student> studentRepository = new YamlRepository<>(new StudentValidator(), Student.class);
        Repository<String, Problem> problemRepository = new YamlRepository<>(new ProblemValidator(), Problem.class);
        Repository<String, Assignment> assignmentRepository = new DatabaseRepository<>(
                new AssignmentValidator(studentRepository, problemRepository),
                db,
                new AssignmentSqlHandler(),
                Assignment.class
        );



        StudentController studentController = new StudentController(studentRepository, assignmentRepository);
        ProblemController problemController = new ProblemController(problemRepository, assignmentRepository);
        AssignmentController assignmentController = new AssignmentController(assignmentRepository, studentRepository);

        new MainMenu(studentController, problemController, assignmentController).run();
    }
}

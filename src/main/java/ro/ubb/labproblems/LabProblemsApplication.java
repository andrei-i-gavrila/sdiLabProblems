package ro.ubb.labproblems;

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
import ro.ubb.labproblems.repository.file.StorageProvider;
import ro.ubb.labproblems.repository.file.XmlRepository;
import ro.ubb.labproblems.ui.MainMenu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

        StorageProvider storage = new StorageProvider();

//        Repository<String, Student> studentRepository = new YamlRepository<>(new StudentValidator(), storage, Student.class);
//        Repository<String, Problem> problemRepository = new YamlRepository<>(new ProblemValidator(), storage, Problem.class);
//        Repository<String, Assignment> assignmentRepository = new YamlRepository<>(new AssignmentValidator(studentRepository, problemRepository), storage, Assignment.class);

        Repository<String, Student> studentRepository = new XmlRepository<>(new StudentValidator(), storage, Student.class);
        Repository<String, Problem> problemRepository = new XmlRepository<>(new ProblemValidator(), storage, Problem.class);
        Repository<String, Assignment> assignmentRepository = new XmlRepository<>(new AssignmentValidator(studentRepository, problemRepository), storage, Assignment.class);


//        Repository<String, Student> studentRepository = new DatabaseRepository<>(new StudentValidator(), db, new StudentSqlHandler<>(), Student.class);
//        Repository<String, Problem> problemRepository = new DatabaseRepository<>(new ProblemValidator(), db, new ProblemSqlHandler(), Problem.class);
//        Repository<String, Assignment> assignmentRepository = new DatabaseRepository<>(new AssignmentValidator(studentRepository, problemRepository), db, new AssignmentSqlHandler(), Assignment.class);



        StudentController studentController = new StudentController(studentRepository, assignmentRepository);
        ProblemController problemController = new ProblemController(problemRepository, assignmentRepository);
        AssignmentController assignmentController = new AssignmentController(assignmentRepository, studentRepository);

        new MainMenu(studentController, problemController, assignmentController).run();
    }
}

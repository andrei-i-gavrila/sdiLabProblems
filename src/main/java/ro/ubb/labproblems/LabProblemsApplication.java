package ro.ubb.labproblems;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ro.ubb.labproblems.controller.AssignmentController;
import ro.ubb.labproblems.controller.ProblemController;
import ro.ubb.labproblems.controller.StudentController;
import ro.ubb.labproblems.domain.entities.Assignment;
import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.domain.validators.AssignmentValidator;
import ro.ubb.labproblems.domain.validators.ProblemValidator;
import ro.ubb.labproblems.domain.validators.StudentValidator;
import ro.ubb.labproblems.repository.InMemoryRepository;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.repository.XmlRepository;
import ro.ubb.labproblems.repository.YamlRepository;
import ro.ubb.labproblems.ui.MainMenu;
import ro.ubb.labproblems.utils.MapSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Public class containing the main function
 */
public class LabProblemsApplication {

    public static void main(String... args) {

        Repository<String, Student> studentRepository = new XmlRepository<>(new StudentValidator(), Student.class);
        Repository<String, Problem> problemRepository = new YamlRepository<>(new ProblemValidator(), Problem.class);
        Repository<String, Assignment> assignmentRepository = new InMemoryRepository<>(new AssignmentValidator(studentRepository, problemRepository));

        StudentController studentController = new StudentController(studentRepository, assignmentRepository);
        ProblemController problemController = new ProblemController(problemRepository, assignmentRepository);
        AssignmentController assignmentController = new AssignmentController(assignmentRepository, studentRepository);

        new MainMenu(studentController, problemController, assignmentController).run();
    }
}

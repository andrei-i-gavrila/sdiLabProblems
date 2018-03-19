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
import ro.ubb.labproblems.repository.InMemoryRepository;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.repository.XmlRepository;
import ro.ubb.labproblems.repository.YamlRepository;
import ro.ubb.labproblems.ui.MainMenu;

/**
 * Public class containing the main function
 */
public class LabProblemsApplication {

    public static void main(String... args) {

        Repository<String, Student> studentRepository = new XmlRepository<>(new StudentValidator(), LabProblemsApplication.class.getResource("/studentRepo.xml").getPath());
        Repository<String, Problem> problemRepository = new YamlRepository<>(new ProblemValidator(), LabProblemsApplication.class.getResource("/problemRepo.yaml").getPath());
        Repository<String, Assignment> assignmentRepository = new InMemoryRepository<>(new AssignmentValidator(studentRepository, problemRepository));

        StudentController studentController = new StudentController(studentRepository);
        ProblemController problemController = new ProblemController(problemRepository);
        AssignmentController assignmentController = new AssignmentController(assignmentRepository);

        new MainMenu(studentController, problemController, assignmentController).run();
    }
}

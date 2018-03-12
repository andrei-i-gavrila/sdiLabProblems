package ro.ubb.labproblems;

import ro.ubb.labproblems.controller.ProblemController;
import ro.ubb.labproblems.controller.StudentController;
import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.domain.validators.ProblemValidator;
import ro.ubb.labproblems.domain.validators.StudentValidator;
import ro.ubb.labproblems.repository.InMemoryRepository;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.ui.MainMenu;

/**
 * Public class containing the main function
 */
public class LabProblemsApplication {

    public static void main(String... args) {

        Repository<Integer, Student> studentRepository = new InMemoryRepository<>(new StudentValidator());
        Repository<String, Problem> problemRepository = new InMemoryRepository<>(new ProblemValidator());

        StudentController studentController = new StudentController(studentRepository);
        ProblemController problemController = new ProblemController(problemRepository);


        new MainMenu(studentController, problemController).run();
    }
}

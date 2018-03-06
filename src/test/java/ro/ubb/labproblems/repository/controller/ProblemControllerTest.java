package ro.ubb.labproblems.repository.controller;

/**
 * Created by Sandy on 3/6/2018.
 */

import org.junit.Test;
import ro.ubb.labproblems.controller.ProblemController;
import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.validators.ProblemValidator;
import ro.ubb.labproblems.repository.InMemoryRepository;
import ro.ubb.labproblems.repository.Repository;

public class ProblemControllerTest {
    private final String title = "title1";
    private final String description = "description";
    Problem problem = new Problem(title, description);
    private Repository<String, Problem> problemRepository = new InMemoryRepository<>(new ProblemValidator());
    ProblemController problemController = new ProblemController(problemRepository);

    @Test
    public void testAdd() {
        assert (problemController.add(title, description) == "Problem registered successfully");
    }

    public void testPrintAll() {
        problemController.add(title, description);
        assert (problemController.showAll().equals(problem.toString()));
    }
}

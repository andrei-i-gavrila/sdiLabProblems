package ro.ubb.labproblems.controller;

import org.junit.Test;
import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.validators.ProblemValidator;
import ro.ubb.labproblems.repository.InMemoryRepository;
import ro.ubb.labproblems.repository.Repository;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Class to test every function of {@link ProblemController ProblemController}, supporting JUnit testing
 */
public class ProblemControllerTest {
    private static final String TITLE1 = "title1";
    private static final String TITLE2 = "title2";
    private static final String DESCRIPTION1 = "DESCRIPTION1";
    private static final String DESCRIPTION2 = "DESCRIPTION1";
    private Problem problem = new Problem(TITLE1, DESCRIPTION1);
    private Repository<String, Problem> problemRepository = new InMemoryRepository<>(new ProblemValidator());
    private ProblemController problemController = new ProblemController(problemRepository, assignmentRepository);

    @Test
    public void testAdd() {
        assertEquals("Problem added successfully", problemController.add(TITLE1, DESCRIPTION1));
        assertTrue(problemRepository.find(TITLE1).isPresent());
    }

    @Test
    public void testRemove() {
        problemController.add(TITLE1, DESCRIPTION1);
        assertEquals("No problem with such title was found", problemController.remove(TITLE2));
        assertEquals("Problem removed successfully", problemController.remove(TITLE1));
        assertFalse(problemRepository.find(TITLE1).isPresent());
    }

    @Test
    public void testUpdate() {
        problemController.add(TITLE1, DESCRIPTION1);
        assertEquals("No problem was found with the given title", problemController.update(TITLE2, DESCRIPTION1));
        assertEquals("Problem updated successfully", problemController.update(TITLE1, DESCRIPTION2));

        Optional<Problem> problemFromRepository = problemRepository.find(TITLE1);
        assertTrue(problemFromRepository.isPresent());
        assertNotEquals(problemFromRepository.get(), problem);
        assertEquals(problemFromRepository.get().getDescription(), DESCRIPTION2);
    }

    @Test
    public void testPrintAll() {
        problemController.add(TITLE1, DESCRIPTION1);
        assertEquals(problem.toString(), problemController.showAll());

        Problem problem2 = new Problem(TITLE2, DESCRIPTION2);
        problemController.add(TITLE2, DESCRIPTION2);
        assertEquals(problem + "\n" + problem2, problemController.showAll());
    }

    @Test
    public void testValidationHappens() {
        assertEquals("The title can't be empty", problemController.add("", DESCRIPTION1));
        assertEquals("The title can't be empty", problemController.update("", DESCRIPTION1));
    }
}

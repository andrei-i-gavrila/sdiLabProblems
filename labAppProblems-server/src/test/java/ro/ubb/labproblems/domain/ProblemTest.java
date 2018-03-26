package ro.ubb.labproblems.domain;

import org.junit.Test;
import ro.ubb.labproblems.domain.entities.Problem;

import static org.junit.Assert.assertEquals;

/**
 * Class to test every function and constructor of {@link Problem problem} entity
 */
public class ProblemTest {

    private static final String initialTitle = "Initial title";
    private static final String newTitle = "New title";
    private static final String initialDescription = "Initial description";
    private static final String newDescription = "New description";

    private Problem problem = new Problem(initialTitle, initialDescription);

    @Test
    public void testGetID() {
        assertEquals(problem.getTitle(), problem.getIdentifier());
    }

    @Test
    public void testGetTitle() {
        assertEquals(initialTitle, problem.getTitle());
    }

    @Test
    public void testSetTitle() {
        problem.setTitle(newTitle);
        assertEquals(newTitle, problem.getTitle());
    }

    @Test
    public void testGetGroup() {
        assertEquals(initialDescription, problem.getDescription());
    }

    @Test
    public void testSetGroup() {
        problem.setDescription(newDescription);
        assertEquals(newDescription, problem.getDescription());
    }

    @Test
    public void testToString() {
        assertEquals(initialTitle + "\n" + initialDescription, problem.toString());
    }
}

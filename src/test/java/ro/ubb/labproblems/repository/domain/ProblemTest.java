package ro.ubb.labproblems.repository.domain;

/**
 * Created by Sandy on 3/5/2018.
 */

import org.junit.Test;
import ro.ubb.labproblems.domain.entities.Problem;

import static org.junit.Assert.assertEquals;

public class ProblemTest {

    private static final String initialTitle = "Initial title";
    private static final String newTitle = "New title";
    private static final String initialDescription = "Initial description";
    private static final String newDescription = "New description";

    private Problem problem = new Problem(initialTitle, initialDescription);

    @Test
    public void testGetID() {
        assertEquals(initialTitle, problem.getIdentifier());
    }

    @Test
    public void testGetTitle() {
        assertEquals(initialTitle, problem.getTitle());
    }

    @Test
    public void testSetName() {
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
}

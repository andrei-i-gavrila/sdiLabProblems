package ro.ubb.labproblems.repository.domain;

/**
 * Created by Sandy on 3/5/2018.
 */

import org.junit.Test;
import ro.ubb.labproblems.domain.entities.Problem;

import static org.junit.Assert.assertThat;

public class ProblemTest {
    private static final Integer ID=new Integer(1);
    private static final String tit1="Initial title";
    private static final String tit2="New title";
    private static final String desc1="Initial description";
    private static final String desc2="New description";

    private Problem problem=new Problem(ID,tit1,desc1);

    @Test
    public void testGetID(){
        assert(problem.getIdentifier()==ID);
    }

    @Test
    public void testGetTitle() {
        assert (problem.getTitle()==tit1);
    }

    @Test
    public void testSetName() {
        problem.setTitle(tit2);
        assert (problem.getTitle()==tit2);
    }

    @Test
    public void testGetGroup() {
        assert (problem.getDescription()==desc1);
    }

    @Test
    public void testSetGroup() {
        problem.setDescription(desc2);
        assert (problem.getDescription()==desc2);
    }
}

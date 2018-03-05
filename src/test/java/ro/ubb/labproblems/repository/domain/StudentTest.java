package ro.ubb.labproblems.repository.domain;

/**
 * Created by Sandy on 3/5/2018.
 */

import org.junit.Test;
import ro.ubb.labproblems.domain.entities.Student;

import static org.junit.Assert.assertThat;

public class StudentTest {
    private static final Integer ID=new Integer(1);
    private static final Integer newID=new Integer(5);
    private static final String name="Initial student";
    private static final String newName="New student";
    private static final Integer gr=new Integer(921);
    private static final Integer newGr=new Integer(927);

    private Student student=new Student(ID,name,gr);

    @Test
    public void testGetID(){
        assert(student.getIdentifier()==ID);
    }

    @Test
    public void testSetID(){
        student.setRegistrationNumber(newID);
        assert(student.getIdentifier()==newID);
    }

    @Test
    public void testGetName() {
        assert (student.getName()==name);
    }

    @Test
    public void testSetName() {
        student.setName(newName);
        assert (student.getName()==newName);
    }

    @Test
    public void testGetGroup() {
        assert (student.getGroupNumber()==gr);
    }

    @Test
    public void testSetGroup() {
        student.setGroupNumber(newGr);
        assert (student.getGroupNumber()==newGr);
    }
}

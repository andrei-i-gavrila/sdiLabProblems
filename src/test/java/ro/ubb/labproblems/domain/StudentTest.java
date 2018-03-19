package ro.ubb.labproblems.domain;

import org.junit.Test;
import ro.ubb.labproblems.domain.entities.Student;

import static org.junit.Assert.assertEquals;

/**
 * Class to test every function and constructor of {@link Student student} entity
 */
public class StudentTest {
    private static final String registrationNumber = "1";
    private static final String newRegistrationNumber = "5";
    private static final String name = "Initial student";
    private static final String newName = "New student";
    private static final Integer groupNumber = 921;
    private static final Integer newGroupNumber = 927;

    private Student student = new Student(registrationNumber, name, groupNumber);

    @Test
    public void testGetID() {
        assertEquals(student.getRegistrationNumber(), student.getIdentifier());
    }

    public void testGetRegistrationNumber() {
        assertEquals(registrationNumber, student.getRegistrationNumber());
    }

    @Test
    public void testSetRegistrationNumber() {
        student.setRegistrationNumber(newRegistrationNumber);
        assertEquals(newRegistrationNumber, student.getIdentifier());
    }

    @Test
    public void testGetName() {
        assertEquals(name, student.getName());
    }

    @Test
    public void testSetName() {
        student.setName(newName);
        assertEquals(newName, student.getName());
    }

    @Test
    public void testGetGroup() {
        assertEquals(groupNumber, student.getGroupNumber());
    }

    @Test
    public void testSetGroup() {
        student.setGroupNumber(newGroupNumber);
        assertEquals(newGroupNumber, student.getGroupNumber());
    }

    @Test
    public void testToString() {
        assertEquals(name + " " + registrationNumber + " " + groupNumber.toString(), student.toString());
    }
}

package ro.ubb.labproblems.controller;

/**
 * Created by Sandy on 3/6/2018.
 */

import org.junit.Test;
import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.domain.validators.StudentValidator;
import ro.ubb.labproblems.repository.InMemoryRepository;
import ro.ubb.labproblems.repository.Repository;

public class StudentControllerTest {
    private final String name = "student1";
    private final Integer regNumber = new Integer(1234);
    private final Integer group = new Integer(925);
    Student student = new Student(regNumber, name, group);
    private Repository<Integer, Student> studentRepository = new InMemoryRepository<>(new StudentValidator());
    private StudentController studentController = new StudentController(studentRepository);

    @Test
    public void testAdd() {
        assert (studentController.add(name, regNumber, group) == "Student added successfully");
    }

    @Test
    public void testRemove() {
        studentController.add(name, regNumber, group);
        assert (studentController.remove(9494).equals("No student with such registration ID was found"));
        assert (studentController.remove(regNumber).equals("Student removed successfully"));
    }

    @Test
    public void testUpdate() {
        studentController.add(name, regNumber, group);
        assert (studentController.update(name, 9494, group).equals("No student was found with the given registration number"));
        assert (studentController.update(name, regNumber, group).equals(("Student updated successfully")));
    }

    @Test
    public void testPrintAll() {
        studentController.add(name, regNumber, group);
        assert (studentController.showAll().equals(student.toString()));
    }
}

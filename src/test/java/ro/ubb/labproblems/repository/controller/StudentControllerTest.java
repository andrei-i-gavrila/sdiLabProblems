package ro.ubb.labproblems.repository.controller;

/**
 * Created by Sandy on 3/6/2018.
 */

import org.junit.Test;
import ro.ubb.labproblems.controller.StudentController;
import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.domain.validators.StudentValidator;
import ro.ubb.labproblems.repository.InMemoryRepository;
import ro.ubb.labproblems.repository.Repository;

public class StudentControllerTest {
    String name = "student1";
    Integer regNumber = new Integer(1234);
    Integer group = new Integer(925);
    Student student = new Student(regNumber, name, group);
    private Repository<Integer, Student> studentRepository = new InMemoryRepository<>(new StudentValidator());
    private StudentController studentController = new StudentController(studentRepository);

    @Test
    public void testAdd() {
        assert (studentController.add(name, regNumber, group) == "Student added successfully");
    }

    @Test
    public void testPrintAll() {
        studentController.add(name, regNumber, group);
        assert (studentController.showAll().equals(student.toString()));
    }
}

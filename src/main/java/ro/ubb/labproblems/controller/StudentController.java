package ro.ubb.labproblems.controller;

import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.utils.IteratorUtils;

import java.util.stream.Collectors;

/**
 * Implements business logic for {@link Student Students}.
 * Functions returns a string that will be directly printed in the ui.
 */
public class StudentController {

    /**
     * Storage for the students
     */
    private final Repository<Integer, Student> studentRepository;

    /**
     * Constructs the controller
     *
     * @param studentRepository {@link Repository} to use for storage
     */
    public StudentController(Repository<Integer, Student> studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Constructs and adds a {@link Student student} to the {@link Repository repository}
     *
     * @param name               The full name of the student
     * @param registrationNumber The registration number of the student
     * @param groupNumber        The group number of the student
     * @return The string containing the success message, or the reason it failed
     */
    public String add(String name, Integer registrationNumber, Integer groupNumber) {
        try {
            return studentRepository.save(new Student(registrationNumber, name, groupNumber))
                    .map(student -> "A student with such registration number already exists")
                    .orElse("Student added successfully");
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    /**
     * Removes the {@link Student student} with the given registration number from the {@link Repository repository}
     *
     * @param registrationNumber The registration number of the {@link Student student} to be removed
     * @return The string containing the success message, or the reason it failed
     */
    public String remove(Integer registrationNumber) {
        return studentRepository.delete(registrationNumber)
                .map(student -> "Student removed successfully")
                .orElse("No student with such registration ID was found");
    }

    /**
     * Updates the {@link Student} with the given registration number to have a new name, and/or a new group number
     *
     * @param name       The current/new name {@link Student student}
     * @param registrationNumber  The identifier(registration number)
     * @param groupNumber The current/new group number
     * @return The string containing the success message, or the reason it failed
     */
    public String update(String name, Integer registrationNumber, Integer groupNumber) {
        try {
            return studentRepository.update(new Student(registrationNumber, name, groupNumber))
                    .map(student -> "No student was found with the given registration number")
                    .orElse("Student updated successfully");
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    /**
     * @return String representation of all {@link Student students} from the {@link Repository repository}
     */
    public String showAll() {
        return IteratorUtils
                .stream(studentRepository.findAll())
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}

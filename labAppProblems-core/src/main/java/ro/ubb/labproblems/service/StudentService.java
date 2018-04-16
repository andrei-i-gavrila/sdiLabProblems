package ro.ubb.labproblems.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ro.ubb.labproblems.model.Student;
import ro.ubb.labproblems.repository.StudentRepository;
import ro.ubb.labproblems.utils.IteratorUtils;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * Implements business logic for {@link Student Students}.
 * Functions returns a string that will be directly printed in the ro.ubb.labproblems.ui.
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;
//    private final AssignmentRepository assignmentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String add(String name, Integer registrationNumber, Integer groupNumber) {
        try {
            return studentRepository
                    .findById(registrationNumber)
                    .map(student -> "Student already exists")
                    .orElseGet(() -> {
                        studentRepository.save(new Student(registrationNumber, name, groupNumber));
                        return "Student saved successfully";
                    });
        } catch (ConstraintViolationException exception) {
            return exception.getMessage();
        }
    }

    public String remove(Integer registrationNumber) {
        try {
            studentRepository.deleteById(registrationNumber);
            return "Student removed successfully";
        } catch (EmptyResultDataAccessException exception) {
            return "No student with registration number " + registrationNumber.toString() + " exists";
        }
    }

    public String update(String name, Integer registrationNumber, Integer groupNumber) {
        try {
            return studentRepository
                    .findById(registrationNumber).map(student -> {
                        student.setName(name);
                        student.setGroupNumber(groupNumber);
                        studentRepository.save(student);
                        return "Student updated successfully";
                    })
                    .orElse("No student with registration number " + registrationNumber.toString() + " exists");
        } catch (ConstraintViolationException exception) {
            return exception.getMessage();
        }
    }

    public String showAll() {
        return IteratorUtils
                .stream(studentRepository.findAll())
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    public String filterByGroup(Integer groupNumber) {
        return IteratorUtils
                .stream(studentRepository.findAll())
                .filter(student -> student.getGroupNumber().equals(groupNumber))
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}

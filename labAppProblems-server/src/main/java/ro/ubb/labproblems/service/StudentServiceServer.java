package ro.ubb.labproblems.service;

import org.springframework.stereotype.Service;
import ro.ubb.labproblems.domain.entities.Assignment;
import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.utils.IteratorUtils;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implements business logic for {@link Student Students}.
 * Functions returns a string that will be directly printed in the ro.ubb.labproblems.ui.
 */
@Service
public class StudentServiceServer implements StudentService {

    /**
     * Storage for the students
     */
    private final Repository<String, Student> studentRepository;
    private final Repository<String, Assignment> assignmentRepository;

    /**
     * Constructs the service
     *
     * @param studentRepository    {@link Repository} to use for storage
     * @param assignmentRepository
     */
    public StudentServiceServer(Repository<String, Student> studentRepository, Repository<String, Assignment> assignmentRepository) {
        this.studentRepository = studentRepository;
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public String add(String name, String registrationNumber, Integer groupNumber) {
        try {
            return studentRepository.save(new Student(registrationNumber, name, groupNumber))
                    .map(student -> "A student with such registration number already exists")
                    .orElse("Student added successfully");
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    @Override
    public String remove(String registrationNumber) {
        return studentRepository.delete(registrationNumber)
                .map(student -> "Student removed successfully")
                .orElse("No student with such registration ID was found");
    }

    @Override
    public String update(String name, String registrationNumber, Integer groupNumber) {
        try {
            return studentRepository.update(new Student(registrationNumber, name, groupNumber))
                    .map(student -> "No student was found with the given registration number")
                    .orElse("Student updated successfully");
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    @Override
    public String showAll() {
        return IteratorUtils
                .stream(studentRepository.findAll())
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String filterByGroup(Integer groupNumber) {
        return IteratorUtils
                .stream(studentRepository.findAll())
                .filter(student -> student.getGroupNumber().equals(groupNumber))
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String bestStudent() {

        return IteratorUtils.stream(studentRepository.findAll())
                .max(Comparator.comparing(this::getStudentAverage))
                .map(Object::toString)
                .orElse("There are no students in the database");
    }

    @Override
    public String getStudentAverageGrade(String studentRegistrationNumber) {
        return studentRepository.find(studentRegistrationNumber)
                .map(student -> student.getName() + ": " + getStudentAverage(student).toString())
                .orElse("There's no student with registration number " + studentRegistrationNumber);
    }

    private Double getStudentAverage(Student student) {
        return IteratorUtils.stream(assignmentRepository.findAll())
                .filter(assignment -> assignment.getStudentRegistrationNumber().equals(student.getRegistrationNumber()))
                .filter(assignment -> Objects.nonNull(assignment.getGrade()))
                .mapToDouble(Assignment::getGrade)
                .average()
                .orElse(0);
    }

}

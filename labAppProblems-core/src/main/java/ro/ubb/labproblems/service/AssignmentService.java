package ro.ubb.labproblems.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ro.ubb.labproblems.model.Student;
import ro.ubb.labproblems.model.Assignment;
import ro.ubb.labproblems.repository.StudentRepository;
import ro.ubb.labproblems.utils.IteratorUtils;
import sun.security.validator.ValidatorException;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    private final CrudRepository<Assignment, String> assignmentRepository;
    private final CrudRepository<Student, String> studentRepository;

    public AssignmentService(CrudRepository<Assignment, String> assignmentRepository, CrudRepository<Student, String> studentRepository) {
        this.assignmentRepository = assignmentRepository;
        this.studentRepository = studentRepository;
    }

    public String assign(Integer StudentRegistrationNumber, String problemTitle) {
        try {
            return assignmentRepository.save(new Assignment())
                    .map(student -> "Problem was already assigned to student")
                    .orElse("Assigned successfully");
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    public String grade(String problemTitle, Integer studentRegistrationNumber, Double grade) {
        Student student = studentRepository.findById(studentRegistrationNumber);
        Assignment assignment = new Assignment(studentRegistrationNumber,problemTitle);
        assignment.setGrade(grade);
        try {
            return assignmentRepository.update(assignment)
                    .map(student -> "Problem was not assigned to student")
                    .orElse("Graded successfully");
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    public String unassign(String problemTitle, Integer studentRegistrationNumber) {
        return assignmentRepository.delete(new Assignment(problemTitle, studentRegistrationNumber).getIdentifier())
                .map(assignment -> "Unassigned successfully")
                .orElse("No assignment was present");
    }

    public String filterByStudent(String registrationNumber) {
        return IteratorUtils.stream(assignmentRepository.findAll())
                .filter(assignment -> assignment.getStudentRegistrationNumber().equals(registrationNumber))
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    public String filterByGrade(String problemTitle, Double grade) {
        return IteratorUtils.stream(assignmentRepository.findAll())
                .filter(assignment -> assignment.getProblemTitle().equals(problemTitle))
                .filter(assignment -> Objects.nonNull(assignment.getGrade()))
                .filter(assignment -> assignment.getGrade() >= grade)
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    public String showAll() {
        return IteratorUtils
                .stream(assignmentRepository.findAll())
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}
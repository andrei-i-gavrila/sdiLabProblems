package ro.ubb.labproblems.service;

import ro.ubb.labproblems.domain.entities.Assignment;
import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.utils.IteratorUtils;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implements business logic for {@link Assignment assignments}.
 * Functions returns a printable version of solution to the ro.ubb.labproblems.ui.
 */
public class AssignmentServiceServer implements AssignmentService {

    /**
     * Storage for the assignments
     */
    private final Repository<String, Assignment> assignmentRepository;
    private final Repository<String, Student> studentRepository;

    /**
     * Constructor for the service
     *
     * @param assignmentRepository {@link Repository} to use for storage
     * @param studentRepository
     */
    public AssignmentServiceServer(Repository<String, Assignment> assignmentRepository, Repository<String, Student> studentRepository) {
        this.assignmentRepository = assignmentRepository;
        this.studentRepository = studentRepository;
    }


    @Override
    public String assign(String problemTitle, String studentRegistrationNumber) {
        try {
            return assignmentRepository.save(new Assignment(problemTitle, studentRegistrationNumber))
                    .map(student -> "Problem was already assigned to student")
                    .orElse("Assigned successfully");
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    @Override
    public String grade(String problemTitle, String studentRegistrationNumber, Double grade) {
        Assignment assignment = new Assignment(problemTitle, studentRegistrationNumber);
        assignment.setGrade(grade);
        try {
            return assignmentRepository.update(assignment)
                    .map(student -> "Problem was not assigned to student")
                    .orElse("Graded successfully");
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    @Override
    public String unassign(String problemTitle, String studentRegistrationNumber) {
        return assignmentRepository.delete(new Assignment(problemTitle, studentRegistrationNumber).getIdentifier())
                .map(assignment -> "Unassigned successfully")
                .orElse("No assignment was present");
    }

    @Override
    public String filterByStudent(String registrationNumber) {
        return IteratorUtils.stream(assignmentRepository.findAll())
                .filter(assignment -> assignment.getStudentRegistrationNumber().equals(registrationNumber))
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String filterByGrade(String problemTitle, Double grade) {
        return IteratorUtils.stream(assignmentRepository.findAll())
                .filter(assignment -> assignment.getProblemTitle().equals(problemTitle))
                .filter(assignment -> Objects.nonNull(assignment.getGrade()))
                .filter(assignment -> assignment.getGrade() >= grade)
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String showAll() {
        return IteratorUtils
                .stream(assignmentRepository.findAll())
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}
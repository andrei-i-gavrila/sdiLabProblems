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
 * Functions returns a printable version of solution to the ui.
 */
public class AssignmentService {

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
    public AssignmentService(Repository<String, Assignment> assignmentRepository, Repository<String, Student> studentRepository) {
        this.assignmentRepository = assignmentRepository;
        this.studentRepository = studentRepository;
    }


    /**
     * Assigns a given problem to a given student
     *
     * @param problemTitle              The title of the problem
     * @param studentRegistrationNumber The registration number of the student
     * @return A message telling the assignment was successful, or the reason why it wasn't
     */
    public String assign(String problemTitle, String studentRegistrationNumber) {
        try {
            return assignmentRepository.save(new Assignment(problemTitle, studentRegistrationNumber))
                    .map(student -> "Problem was already assigned to student")
                    .orElse("Assigned successfully");
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    /**
     * Assigns a grade to a given assignment
     *
     * @param problemTitle              The title of the problem
     * @param studentRegistrationNumber The registration number of the student
     * @param grade                     The given grade
     * @return A message telling the assignment was successful, or the reason why it wasn't
     */
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

    /**
     * Unassigns a problem from a student
     *
     * @param problemTitle              The title of the problem
     * @param studentRegistrationNumber The registration number of the student
     * @return A message telling the unassignment was successful, or the reason why it wasn't
     */
    public String unassign(String problemTitle, String studentRegistrationNumber) {
        return assignmentRepository.delete(new Assignment(problemTitle, studentRegistrationNumber).getIdentifier())
                .map(assignment -> "Unassigned successfully")
                .orElse("No assignment was present");
    }

    /**
     * Returns those assignments that are assigned to a given student
     *
     * @param registrationNumber The registration number of the student
     * @return A list of filtered assignments
     */
    public String filterByStudent(String registrationNumber) {
        return IteratorUtils.stream(assignmentRepository.findAll())
                .filter(assignment -> assignment.getStudentRegistrationNumber().equals(registrationNumber))
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Returns those assignments that contain a given problem, and the grade given for it is greater or equal to the given grade
     *
     * @param problemTitle The title of the problem
     * @return A list of filtered assignments
     */
    public String filterByGrade(String problemTitle, Double grade) {
        return IteratorUtils.stream(assignmentRepository.findAll())
                .filter(assignment -> assignment.getProblemTitle().equals(problemTitle))
                .filter(assignment -> Objects.nonNull(assignment.getGrade()))
                .filter(assignment -> assignment.getGrade() >= grade)
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    /**
     * @return String representation of all {@link Assignment assignments} from the {@link Repository repository}
     */
    public String showAll() {
        return IteratorUtils
                .stream(assignmentRepository.findAll())
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}
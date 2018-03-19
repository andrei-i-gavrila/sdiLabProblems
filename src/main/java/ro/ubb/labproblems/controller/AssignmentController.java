package ro.ubb.labproblems.controller;

import ro.ubb.labproblems.domain.entities.Assignment;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.utils.IteratorUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implements business logic for {@link Assignment assignments}.
 * Functions returns a printable version of solution to the ui.
 */
public class AssignmentController {

    /**
     * Storage for the assignments
     */
    private final Repository<String, Assignment> assignmentRepository;

    /**
     * Constructor for the controller
     *
     * @param assignmentRepository {@link Repository} to use for storage
     */
    public AssignmentController(Repository<String, Assignment> assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }


    /**
     * Assigns a given problem to a given student
     * @param problemTitle The title of the problem
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
     * @param problemTitle The title of the problem
     * @param studentRegistrationNumber The registration number of the student
     * @param grade The given grade
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
     * @param problemTitle The title of the problem
     * @param studentRegistrationNumber The registration number of the student
     * @return A message telling the unassignment was successful, or the reason why it wasn't
     */
    public String unassign(String problemTitle, String studentRegistrationNumber) {
        return assignmentRepository.delete(new Assignment(problemTitle, studentRegistrationNumber).getIdentifier())
                .map(assignment -> "Unassigned successfully")
                .orElse("No assignment was present");
    }

    /**
     * Returns all assignments as a list
     * @return
     */
    public List<Assignment> getAllAssignments() {
        return IteratorUtils.toList(assignmentRepository.findAll());
    }

    /**
     * Finds the problem that was assigned most times
     * @return The title of the most assigned problem
     */
    public String mostAssignedProblem() {
        List<Assignment> assignments = getAllAssignments();
        Map<String, Integer> freqs = new HashMap<>();
        for (Assignment a : assignments) {
            freqs.merge(a.getProblemTitle(),
                    1,
                    Integer::sum);
        }
        Integer count = freqs.values().stream().max(Comparator.comparingInt(x -> (int) x)).get();
        return freqs.entrySet().stream().filter(x -> x.getValue().equals(count)).findFirst().get().getKey();
    }

    /**
     * Returns those assignments that are assigned to a given student
     * @param registrationNumber The registration number of the student
     * @return A list of filtered assignments
     */
    public List<Assignment> filterByStudent(String registrationNumber) {
        return getAllAssignments()
                .stream()
                .filter(assignment -> (assignment.getStudentRegistrationNumber().equals(registrationNumber)))
                .collect(Collectors.toList());
    }

    /**
     * Returns those assignments that contain a given problem, and the grade given for it is greater or equal to 8
     * @param problemTitle The title of the problem
     * @return A list of filtered assignments
     */
    public List<Assignment> filterByGrade(String problemTitle) {
        return getAllAssignments()
                .stream()
                .filter(assignment -> (assignment.getProblemTitle().equals(problemTitle) && assignment.getGrade()>=8))
                .collect(Collectors.toList());
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


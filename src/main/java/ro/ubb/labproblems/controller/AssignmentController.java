package ro.ubb.labproblems.controller;

import ro.ubb.labproblems.domain.entities.Assignment;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.utils.IteratorUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class AssignmentController {

    private final Repository<String, Assignment> assignmentRepository;


    public AssignmentController(Repository<String, Assignment> assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }


    public String assign(String problemTitle, String studentRegistrationNumber) {
        try {
            return assignmentRepository.save(new Assignment(problemTitle, studentRegistrationNumber))
                    .map(student -> "Problem was already assigned to student")
                    .orElse("Assigned successfully");
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

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

    public String unassign(String problemTitle, String studentRegistrationNumber) {
        return assignmentRepository.delete(new Assignment(problemTitle, studentRegistrationNumber).getIdentifier())
                .map(assignment -> "Unassigned successfully")
                .orElse("No assignment was present");
    }

    public Set<Assignment> getAllAssignments() {
        Iterable<Assignment> assignments = assignmentRepository.findAll();
        return StreamSupport.stream(assignments.spliterator(), false).collect(Collectors.toSet());
    }

    public String mostAssignedProblem() {
        Set<Assignment> assignments = getAllAssignments();
        Map<String, Integer> freqs = new HashMap<>();
        for (Assignment a : assignments) {
            freqs.merge(a.getProblemTitle(),                  // key = char
                    1,                  // value to merge
                    Integer::sum);      // counting
        }
        Integer count = freqs.values().stream().max(Comparator.comparingInt(x -> (int) x)).get();
        return freqs.entrySet().stream().filter(x -> x.getValue() == count).findFirst().get().getKey();
    }

    public Set<Assignment> filterByStudent(Integer registrationNumber) {
        Set<Assignment> assignments = getAllAssignments();
        return assignments.stream().filter(x -> (x.getStudentRegistrationNumber().equals(registrationNumber))).collect(Collectors.toSet());
    }

    public String showAll() {
        return IteratorUtils
                .stream(assignmentRepository.findAll())
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}


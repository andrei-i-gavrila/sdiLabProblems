package ro.ubb.labproblems.controller;

import ro.ubb.labproblems.domain.entities.Assignment;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.utils.IteratorUtils;

import java.util.stream.Collectors;

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

    public String unnassign(String problemTitle, String studentRegistrationNumber) {
        return assignmentRepository.delete(new Assignment(problemTitle, studentRegistrationNumber).getIdentifier())
                .map(assignment -> "Unassigned successfully")
                .orElse("No assignment was present");
    }

    public String showAll() {
        return IteratorUtils
                .stream(assignmentRepository.findAll())
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}

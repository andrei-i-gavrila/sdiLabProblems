package ro.ubb.labproblems.service;

import ro.ubb.labproblems.domain.entities.Assignment;
import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.utils.IteratorUtils;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implements business logic for {@link Problem Problems}.
 * Functions returns a string that will be directly printed in the ui.
 */
public class ProblemServiceServer implements ProblemService {

    /**
     * Storage for the problems
     */
    private final Repository<String, Problem> problemRepository;
    private final Repository<String, Assignment> assignmentRepository;

    /**
     * Constructs the service
     *
     * @param problemRepository    {@link Repository} to use for storage
     * @param assignmentRepository
     */
    public ProblemServiceServer(Repository<String, Problem> problemRepository, Repository<String, Assignment> assignmentRepository) {
        this.problemRepository = problemRepository;
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public String add(String title, String description) {
        try {
            return problemRepository.save(new Problem(title, description))
                    .map(student -> "A problem with such title already exists")
                    .orElse("Problem added successfully");
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    @Override
    public String remove(String title) {
        return problemRepository.delete(title)
                .map(student -> "Problem removed successfully")
                .orElse("No problem with such title was found");
    }

    @Override
    public String update(String title, String description) {
        try {
            return problemRepository.update(new Problem(title, description))
                    .map(student -> "No problem was found with the given title")
                    .orElse("Problem updated successfully");
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }


    @Override
    public String showAll() {
        return IteratorUtils
                .stream(problemRepository.findAll())
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String mostAssignedProblem() {
        return IteratorUtils.stream(assignmentRepository.findAll())
                .collect(Collectors.toMap(Assignment::getProblemTitle, assignment -> 1, Integer::sum))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Object::toString)
                .orElse("No problems are assigned");
    }
}

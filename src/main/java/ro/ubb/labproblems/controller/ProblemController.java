package ro.ubb.labproblems.controller;

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
public class ProblemController {

    /**
     * Storage for the problems
     */
    private Repository<String, Problem> problemRepository;
    private Repository<String, Assignment> assignmentRepository;

    /**
     * Constructs the controller
     *
     * @param problemRepository    {@link Repository} to use for storage
     * @param assignmentRepository
     */
    public ProblemController(Repository<String, Problem> problemRepository, Repository<String, Assignment> assignmentRepository) {
        this.problemRepository = problemRepository;
        this.assignmentRepository = assignmentRepository;
    }

    /**
     * Constructs and adds a {@link Problem problem} to the {@link Repository repository}
     *
     * @param title       The title of the problem
     * @param description The description of the problem
     * @return The string containing the success message, or the reason it failed
     */
    public String add(String title, String description) {
        try {
            return problemRepository.save(new Problem(title, description))
                    .map(student -> "A problem with such title already exists")
                    .orElse("Problem added successfully");
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    /**
     * Removes the {@link Problem problem} with the given title from the {@link Repository repository}
     *
     * @param title The title of the {@link Problem problem} to be removed
     * @return The string containing the success message, or the reason it failed
     */
    public String remove(String title) {
        return problemRepository.delete(title)
                .map(student -> "Problem removed successfully")
                .orElse("No problem with such title was found");
    }

    /**
     * Updates the {@link Problem} with the given title to have a new description
     *
     * @param title       identifier for the {@link Problem problem}
     * @param description new description
     * @return The string containing the success message, or the reason it failed
     */
    public String update(String title, String description) {
        try {
            return problemRepository.update(new Problem(title, description))
                    .map(student -> "No problem was found with the given title")
                    .orElse("Problem updated successfully");
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }


    /**
     * @return String representation of all {@link Problem problems} from the {@link Repository repository}
     */
    public String showAll() {
        return IteratorUtils
                .stream(problemRepository.findAll())
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Finds the problem that was assigned most times
     *
     * @return The title of the most assigned problem
     */
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

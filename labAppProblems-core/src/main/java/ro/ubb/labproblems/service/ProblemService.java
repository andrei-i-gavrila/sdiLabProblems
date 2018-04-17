package ro.ubb.labproblems.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import ro.ubb.labproblems.model.Problem;
import ro.ubb.labproblems.model.Assignment;
import ro.ubb.labproblems.repository.ProblemRepository;
import ro.ubb.labproblems.repository.StudentRepository;
import ro.ubb.labproblems.utils.IteratorUtils;
import sun.security.validator.ValidatorException;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Service
public class ProblemService {

    /**
     * Storage for the problems
     */
    private final ProblemRepository problemRepository;
    //private final Repository<String, Assignment> assignmentRepository;

    /**
     * Constructs the service
     *
     * @param problemRepository    {@link Repository} to use for storage
     */
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public String add(String title, String description) {
        try {
            return problemRepository
                    .findById(title)
                    .map(problem -> "Problem already exists")
                    .orElseGet(() -> {
                        problemRepository.save(new Problem(title,description));
                        return "Problem saved successfully";
                    });
        } catch (ConstraintViolationException exception) {
            return exception.getMessage();
        }
    }

    public String remove(String title) {
        try {
            problemRepository.deleteById(title);
            return "Problem removed successfully";
        } catch (EmptyResultDataAccessException exception) {
            return "No problem with title " + title + " exists";
        }
    }

    public String update(String title, String description) {
        try {
            return problemRepository
                    .findById(title).map(problem -> {
                        problem.setTitle(title);
                        problem.setDescription(description);
                        problemRepository.save(problem);
                        return "Problem updated successfully";
                    })
                    .orElse("No problem with title " + title + " exists");
        } catch (ConstraintViolationException exception) {
            return exception.getMessage();
        }
    }


    public String showAll() {
        return IteratorUtils
                .stream(problemRepository.findAll())
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    /*public String mostAssignedProblem() {
        return IteratorUtils.stream(assignmentRepository.findAll())
                .collect(Collectors.toMap(Assignment::getProblemTitle, assignment -> 1, Integer::sum))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Object::toString)
                .orElse("No problems are assigned");
    }*/
}

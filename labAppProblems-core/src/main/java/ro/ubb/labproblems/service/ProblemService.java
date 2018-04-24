package ro.ubb.labproblems.service;

import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import ro.ubb.labproblems.repository.ProblemRepository;
import ro.ubb.labproblems.utils.IteratorUtils;
import ro.ubb.labproblems.model.Problem;
import java.util.List;
import java.util.Optional;



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
        return null;

    }

    public String remove(String title) {
        return null;

    }

    public String update(String title, String description) {
        return null;

    }


    public String showAll() {
        return IteratorUtils
                .stream(problemRepository.findAll())
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    public List<Problem> getAll() {
        return problemRepository.findAll();
    }

    public Optional<Problem> get(Integer id) {
        return problemRepository.findById(id);
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

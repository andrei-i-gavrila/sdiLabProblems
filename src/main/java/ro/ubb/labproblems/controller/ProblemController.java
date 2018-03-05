package ro.ubb.labproblems.controller;

/**
 * Created by Sandy on 3/5/2018.
 */

import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.utils.IteratorUtils;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ProblemController {

    private Repository<Integer, Problem> problemRepository;

    public ProblemController(Repository<Integer, Problem> problemRepository) {
        this.problemRepository = problemRepository;
    }

    public String add(Integer regNr, String tit, String desc) {
        try {
            problemRepository.save(new Problem(regNr,tit,desc));
            return "Problem registered successfully";
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    public Set<Problem> getAllProblems() {
        Iterable<Problem> problems = problemRepository.findAll();
        return StreamSupport.stream(problems.spliterator(), false).collect(Collectors.toSet());
    }

    public String showAll() {
        return IteratorUtils.stream(problemRepository.findAll()).map(Object::toString).collect(Collectors.joining("\n"));
    }
}

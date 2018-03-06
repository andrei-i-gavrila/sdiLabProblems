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

    private Repository<String, Problem> problemRepository;

    public ProblemController(Repository<String, Problem> problemRepository) {
        this.problemRepository = problemRepository;
    }

    public String add(String title, String description) {
        try {
            problemRepository.save(new Problem(title, description));
            return "Problem registered successfully";
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }


    public String showAll() {
        return IteratorUtils
                .stream(problemRepository.findAll())
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}

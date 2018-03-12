package ro.ubb.labproblems.controller;

/**
 * Created by Sandy on 3/5/2018.
 */

import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.utils.IteratorUtils;

import java.util.stream.Collectors;

public class ProblemController {

    private Repository<String, Problem> problemRepository;

    public ProblemController(Repository<String, Problem> problemRepository) {
        this.problemRepository = problemRepository;
    }

    public String add(String title, String description) {
        try {
            return problemRepository.save(new Problem(title, description))
                    .map(student -> "A problem with such title already exists")
                    .orElse("Problem added successfully");
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    public String remove(String title) {
        return problemRepository.delete(title)
                .map(student -> "Problem removed successfully")
                .orElse("No problem with such title was found");
    }

    public String update(String title, String description) {
        try {
            return problemRepository.update(new Problem(title, description))
                    .map(student -> "No problem was found with the given title")
                    .orElse("Problem updated successfully");
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

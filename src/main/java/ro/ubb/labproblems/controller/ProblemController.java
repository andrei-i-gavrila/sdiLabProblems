package ro.ubb.labproblems.controller;

/**
 * Created by Sandy on 3/5/2018.
 */

import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.utils.IteratorUtils;

import java.util.Optional;
import java.util.stream.Collectors;

public class ProblemController {

    private Repository<String, Problem> problemRepository;

    public ProblemController(Repository<String, Problem> problemRepository) {
        this.problemRepository = problemRepository;
    }

    public String add(String title, String description) {
        try {
            if (problemRepository.save(new Problem(title, description)).equals(Optional.empty()))
                return "Problem added successfully";
            else
                return "A problem with such title already exists";
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    public String remove(String title) {
        if (problemRepository.delete(title).equals(Optional.empty()))
            return "No problem with such title was found";
        else
            return "Problem removed successfully";

    }

    public String update(String title, String description) {
        try {
            if (problemRepository.update(new Problem(title, description)).equals(Optional.empty()))
                return "Problem updated successfully";
            else
                return "No problem was found with the given title";
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

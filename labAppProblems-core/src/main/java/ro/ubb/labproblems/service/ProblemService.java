package ro.ubb.labproblems.service;

import org.springframework.stereotype.Service;
import ro.ubb.labproblems.model.Problem;
import ro.ubb.labproblems.repository.ProblemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository=problemRepository;
    }

    public Problem save(Problem problem) {
        return problemRepository.save(problem);
    }

    public void delete(Integer id) {
        problemRepository.deleteById(id);
    }

    public List<Problem> getAll() {
        return problemRepository.findAll();
    }

    public Optional<Problem> get(Integer id) {
        return problemRepository.findById(id);
    }

}

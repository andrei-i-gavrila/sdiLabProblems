package ro.ubb.labproblems.service;

import org.springframework.stereotype.Service;
import ro.ubb.labproblems.model.Assignment;
import ro.ubb.labproblems.repository.AssignmentRepository;
import ro.ubb.labproblems.repository.ProblemRepository;
import ro.ubb.labproblems.repository.StudentRepository;
import ro.ubb.labproblems.utils.IteratorUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository=assignmentRepository;
    }

    public Assignment save(Assignment assignment) { return assignmentRepository.save(assignment);}

    public void delete(Integer id) {
        assignmentRepository.deleteById(id);
    }

    public List<Assignment> getAll() { return assignmentRepository.findAll();}

    public Optional<Assignment> get(Integer id) { return  assignmentRepository.findById(id);}

    /*public String assign(Integer studentRegistrationNumber, String problemTitle) {
        return null;
    }

    public String grade(String problemTitle, Integer studentRegistrationNumber, Double grade) {
        return null;

    }

    public String unassign(String problemTitle, Integer studentRegistrationNumber) {
        return null;

    }


    public String showAll() {
        return IteratorUtils
                .stream(assignmentRepository.findAll())
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }*/
}
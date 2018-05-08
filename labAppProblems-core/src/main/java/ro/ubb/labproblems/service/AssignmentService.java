package ro.ubb.labproblems.service;

import org.springframework.stereotype.Service;
import ro.ubb.labproblems.model.Assignment;
import ro.ubb.labproblems.model.Problem;
import ro.ubb.labproblems.model.Student;
import ro.ubb.labproblems.repository.AssignmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public Assignment save(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public void delete(Integer id) {
        assignmentRepository.deleteById(id);
    }

    public List<Assignment> getAll() {
        return assignmentRepository.findAll();
    }

    public Optional<Assignment> get(Integer id) {
        return assignmentRepository.findById(id);
    }

    public void deleteAllOfStudent(Student student) {
        assignmentRepository.deleteByStudent(student);
    }

    public void deleteAllOfProblem(Problem problem) {
        assignmentRepository.deleteByProblem(problem);
    }
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

package ro.ubb.labproblems.service;

import org.springframework.stereotype.Service;
import ro.ubb.labproblems.repository.AssignmentRepository;
import ro.ubb.labproblems.repository.ProblemRepository;
import ro.ubb.labproblems.repository.StudentRepository;
import ro.ubb.labproblems.utils.IteratorUtils;

import java.util.stream.Collectors;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final StudentRepository studentRepository;
    private final ProblemRepository problemRepository;

    public AssignmentService(AssignmentRepository assignmentRepository, StudentRepository studentRepository, ProblemRepository problemRepository) {
        this.assignmentRepository = assignmentRepository;
        this.studentRepository = studentRepository;
        this.problemRepository = problemRepository;
    }

    public String assign(Integer studentRegistrationNumber, String problemTitle) {
        return null;
    }

    public String grade(String problemTitle, Integer studentRegistrationNumber, Double grade) {
        return null;

    }

    public String unassign(String problemTitle, Integer studentRegistrationNumber) {
        return null;

    }
//
//    public String filterByStudent(String registrationNumber) {
//        return IteratorUtils.stream(assignmentRepository.findAll())
//                .filter(assignment -> assignment.getStudentRegistrationNumber().equals(registrationNumber))
//                .map(Object::toString)
//                .collect(Collectors.joining("\n"));
//    }
//
//    public String filterByGrade(String problemTitle, Double grade) {
//        return IteratorUtils.stream(assignmentRepository.findAll())
//                .filter(assignment -> assignment.getProblemTitle().equals(problemTitle))
//                .filter(assignment -> Objects.nonNull(assignment.getGrade()))
//                .filter(assignment -> assignment.getGrade() >= grade)
//                .map(Object::toString)
//                .collect(Collectors.joining("\n"));
//    }

    public String showAll() {
        return IteratorUtils
                .stream(assignmentRepository.findAll())
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}
package ro.ubb.labproblems.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ro.ubb.labproblems.model.Assignment;
import ro.ubb.labproblems.model.AssignmentId;
import ro.ubb.labproblems.model.Problem;
import ro.ubb.labproblems.model.Student;
import ro.ubb.labproblems.repository.AssignmentRepository;
import ro.ubb.labproblems.repository.ProblemRepository;
import ro.ubb.labproblems.repository.StudentRepository;
import ro.ubb.labproblems.utils.IteratorUtils;

import javax.validation.ConstraintViolationException;
import java.util.Optional;
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
        Optional<Student> student = studentRepository.findById(studentRegistrationNumber);
        if (!student.isPresent()) {
            return "Student does not exist";
        }

        Optional<Problem> problem = problemRepository.findById(problemTitle);
        if (!problem.isPresent()) {
            return "Problem does not exist";
        }

        try {
            return assignmentRepository
                    .findById(new AssignmentId(problem.get(), student.get()))
                    .map(assignment -> "Assignment already exists")
                    .orElseGet(() -> {
                        assignmentRepository.save(new Assignment(problem.get(), student.get(), null));
                        return "Assignment saved successfully";
                    });
        } catch (ConstraintViolationException exception) {
            return exception.getMessage();
        }
    }

    public String grade(String problemTitle, Integer studentRegistrationNumber, Double grade) {
        Optional<Student> student = studentRepository.findById(studentRegistrationNumber);
        if (!student.isPresent()) {
            return "Student does not exist";
        }

        Optional<Problem> problem = problemRepository.findById(problemTitle);
        if (!problem.isPresent()) {
            return "Problem does not exist";
        }

        try {
            return assignmentRepository
                    .findById(new AssignmentId(problem.get(), student.get()))
                    .map(assignment -> {
                        assignment.setGrade(grade);
                        assignmentRepository.save(assignment);
                        return "Assignment graded successfully";
                    })
                    .orElse("No assignment found");
        } catch (ConstraintViolationException exception) {
            return exception.getMessage();
        }
    }

    public String unassign(String problemTitle, Integer studentRegistrationNumber) {
        Optional<Student> student = studentRepository.findById(studentRegistrationNumber);
        if (!student.isPresent()) {
            return "Student does not exist";
        }

        Optional<Problem> problem = problemRepository.findById(problemTitle);
        if (!problem.isPresent()) {
            return "Problem does not exist";
        }
        try {
            assignmentRepository.deleteById(new AssignmentId(problem.get(), student.get()));
            return "Assignment removed successfully";
        } catch (EmptyResultDataAccessException exception) {
            return "No assignment found";
        }
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
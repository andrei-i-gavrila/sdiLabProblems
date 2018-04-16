package ro.ubb.labproblems.service;

import org.springframework.stereotype.Service;

@Service
public class AssignmentService {
//
//    private final CrudRepository<Assignment, String> assignmentRepository;
//    private final CrudRepository<Student, String> studentRepository;
//
//    public AssignmentService(CrudRepository<Assignment, String> assignmentRepository, CrudRepository<Student, String> studentRepository) {
//        this.assignmentRepository = assignmentRepository;
//        this.studentRepository = studentRepository;
//    }
//
//
//    @Override
//    public String assign(String problemTitle, Integer studentRegistrationNumber) {
//        try {
//            return assignmentRepository.save(new Assignment(problemTitle, studentRegistrationNumber))
//                    .map(student -> "Problem was already assigned to student")
//                    .orElse("Assigned successfully");
//        } catch (ValidatorException e) {
//            return e.getMessage();
//        }
//    }
//
//    @Override
//    public String grade(String problemTitle, Integer studentRegistrationNumber, Double grade) {
//        Assignment assignment = new Assignment(problemTitle, studentRegistrationNumber);
//        assignment.setGrade(grade);
//        try {
//            return assignmentRepository.update(assignment)
//                    .map(student -> "Problem was not assigned to student")
//                    .orElse("Graded successfully");
//        } catch (ValidatorException e) {
//            return e.getMessage();
//        }
//    }
//
//    @Override
//    public String unassign(String problemTitle, Integer studentRegistrationNumber) {
//        return assignmentRepository.delete(new Assignment(problemTitle, studentRegistrationNumber).getIdentifier())
//                .map(assignment -> "Unassigned successfully")
//                .orElse("No assignment was present");
//    }
//
//    @Override
//    public String filterByStudent(String registrationNumber) {
//        return IteratorUtils.stream(assignmentRepository.findAll())
//                .filter(assignment -> assignment.getStudentRegistrationNumber().equals(registrationNumber))
//                .map(Object::toString)
//                .collect(Collectors.joining("\n"));
//    }
//
//    @Override
//    public String filterByGrade(String problemTitle, Double grade) {
//        return IteratorUtils.stream(assignmentRepository.findAll())
//                .filter(assignment -> assignment.getProblemTitle().equals(problemTitle))
//                .filter(assignment -> Objects.nonNull(assignment.getGrade()))
//                .filter(assignment -> assignment.getGrade() >= grade)
//                .map(Object::toString)
//                .collect(Collectors.joining("\n"));
//    }
//
//    @Override
//    public String showAll() {
//        return IteratorUtils
//                .stream(assignmentRepository.findAll())
//                .map(Object::toString)
//                .collect(Collectors.joining("\n"));
//    }
}
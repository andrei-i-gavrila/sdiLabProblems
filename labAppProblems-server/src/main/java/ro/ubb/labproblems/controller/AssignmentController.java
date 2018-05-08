package ro.ubb.labproblems.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.ubb.labproblems.ErrorDto;
import ro.ubb.labproblems.Response;
import ro.ubb.labproblems.dto.AssignmentDto;
import ro.ubb.labproblems.mapper.AssignmentMapper;
import ro.ubb.labproblems.model.Assignment;
import ro.ubb.labproblems.model.Problem;
import ro.ubb.labproblems.model.Student;
import ro.ubb.labproblems.service.AssignmentService;
import ro.ubb.labproblems.service.ProblemService;
import ro.ubb.labproblems.service.StudentService;
import ro.ubb.labproblems.validator.AssignmentValidator;
import ro.ubb.labproblems.validator.ValidationErrorDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/assignments", produces = MediaType.APPLICATION_JSON_VALUE)
public class AssignmentController {
    private static final Logger log = LoggerFactory.getLogger(AssignmentController.class);

    private AssignmentService assignmentService;
    private AssignmentMapper assignmentMapper;
    private AssignmentValidator assignmentValidator;
    private StudentService studentService;
    private ProblemService problemService;

    public AssignmentController(AssignmentService assignmentService, AssignmentMapper assignmentMapper, AssignmentValidator assignmentValidator, StudentService studentService, ProblemService problemService) {
        this.assignmentService = assignmentService;
        this.assignmentMapper = assignmentMapper;
        this.assignmentValidator = assignmentValidator;
        this.studentService = studentService;
        this.problemService = problemService;
    }


    @GetMapping
    Response<List<AssignmentDto>> index() {
        log.info("AssignmentController getAll");

        List<AssignmentDto> assignmentDtoList = assignmentMapper.toDtoList(assignmentService.getAll());

        log.info("AssignmentController getAll: {}", assignmentDtoList);

        return Response.success(assignmentDtoList);
    }

    @PostMapping
    Response<AssignmentDto> create(@RequestBody Map<String, Integer> params) {
        Integer studentId = params.get("studentId");
        Integer problemId = params.get("problemId");
        log.info("AssignmentController create: {}, {}", studentId, problemId);

        Optional<Student> student = studentService.get(studentId);
        if (!student.isPresent()) {
            log.info("AssignmentController create student not found: {}", studentId);
            return Response.fail(new ErrorDto("Student with id {} not found", studentId));
        }

        Optional<Problem> problem = problemService.get(problemId);
        if (!problem.isPresent()) {
            log.info("AssignmentController create problem not found: {}", problemId);
            return Response.fail(new ErrorDto("Problem with id {} not found", problemId));
        }

        Assignment assignment = new Assignment();
        assignment.setStudent(student.get());
        assignment.setProblem(problem.get());
        Optional<List<ValidationErrorDto>> validationErrors = assignmentValidator.validate(assignment);
        if (validationErrors.isPresent()) {
            log.info("AssignmentController create errors: {}", validationErrors.get());
            return Response.fail(validationErrors.get());
        }

        assignment = assignmentService.save(assignment);
        AssignmentDto assignmentDto = assignmentMapper.toDto(assignment);

        log.info("AssignmentController create: {}", assignmentDto);
        return Response.success(assignmentDto);
    }

    @GetMapping(path = "/{id}")
    Response<AssignmentDto> show(@PathVariable Integer id) {
        log.info("AssignmentController show: {}", id);

        Optional<Assignment> optionalAssignment = assignmentService.get(id);

        if (!optionalAssignment.isPresent()) {
            log.info("Assignment controller show: assignment not found");
            return Response.fail(new ErrorDto("Assignment with id {} does not exist", id));
        }

        AssignmentDto assignmentDto = assignmentMapper.toDto(optionalAssignment.get());

        log.info("Assignment Controller show: {}", assignmentDto);
        return Response.success(assignmentDto);
    }

    @DeleteMapping(path = "/{id}")
    Response<AssignmentDto> delete(@PathVariable Integer id) {
        log.info("Assignment Controller delete: {}");

        assignmentService.delete(id);

        return Response.success();
    }
}

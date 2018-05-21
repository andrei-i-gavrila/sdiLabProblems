package ro.ubb.labproblems.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.ubb.labproblems.ErrorDto;
import ro.ubb.labproblems.Response;
import ro.ubb.labproblems.dto.ProblemDto;
import ro.ubb.labproblems.mapper.ProblemMapper;
import ro.ubb.labproblems.model.Problem;
import ro.ubb.labproblems.service.AssignmentService;
import ro.ubb.labproblems.service.ProblemService;
import ro.ubb.labproblems.validator.ProblemValidator;
import ro.ubb.labproblems.validator.ValidationErrorDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/problems", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProblemController {
    private static final Logger log = LoggerFactory.getLogger(ProblemController.class);

    private ProblemService problemService;
    private ProblemMapper problemMapper;
    private ProblemValidator problemValidator;
    private AssignmentService assignmentService;

    public ProblemController(ProblemService problemService, ProblemMapper problemMapper, ProblemValidator problemValidator, AssignmentService assignmentService) {
        this.problemService = problemService;
        this.problemMapper = problemMapper;
        this.problemValidator = problemValidator;
        this.assignmentService = assignmentService;
    }

    @GetMapping
    public Response<List<ProblemDto>> index() {
        log.info("ProblemController getAll");

        List<ProblemDto> problemDtoList = problemMapper.toDtoList(problemService.getAll());

        log.info("ProblemController getAll: {}", problemDtoList);

        return Response.success(problemDtoList);
    }

    @GetMapping(path = "/{id}")
    public Response<ProblemDto> show(@PathVariable Integer id) {
        log.info("ProblemController show: {}", id);

        Optional<Problem> optionalProblem = problemService.get(id);

        if (!optionalProblem.isPresent()) {
            log.info("Problem controller show: problem not found");
            return Response.fail(new ErrorDto("Problem with id {} does not exist", id));
        }

        ProblemDto problemDto = problemMapper.toDto(optionalProblem.get());

        log.info("Problem Controller show: {}", problemDto);

        return Response.success(problemDto);
    }

    @PostMapping
    public Response<ProblemDto> create(@RequestBody ProblemDto problemDto) {
        log.info("ProblemController create: {}", problemDto);

        Problem problem = problemMapper.toEntity(problemDto);

        Optional<List<ValidationErrorDto>> validationErrors = problemValidator.validate(problem);
        if (validationErrors.isPresent()) {
            log.info("ProblemController create errors: {}", validationErrors.get());
            return Response.fail(validationErrors.get());
        }

        problem = problemService.save(problem);
        problemDto = problemMapper.toDto(problem);

        log.info("ProblemController create: {}", problemDto);
        return Response.success(problemDto);
    }

    @DeleteMapping(path = "/{id}")
    Response<ProblemDto> delete(@PathVariable Integer id) {
        log.info("Problem Controller delete: {}");

        problemService.get(id).ifPresent(problem -> assignmentService.deleteAllOfProblem(problem));

        problemService.delete(id);

        return Response.success();
    }

    @PutMapping
    Response<ProblemDto> update(@RequestBody ProblemDto problemDto) {
        return problemService.get(problemDto.getId())
                .map(student -> Response.success(problemMapper.toDto(problemService.save(problemMapper.updateToEntity(problemDto, student)))))
                .orElseGet(() -> Response.fail(new ErrorDto("Cannot find student with id {}", problemDto.getId())));
    }
}

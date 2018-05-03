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
import ro.ubb.labproblems.service.ProblemService;
import ro.ubb.labproblems.validator.ValidationErrorDto;
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

    public ProblemController(ProblemService problemService, ProblemMapper problemMapper, ProblemValidator problemValidator) {
        this.problemService = problemService;
        this.problemMapper = problemMapper;
        this.problemValidator = problemValidator;
    }

    @RequestMapping(method = RequestMethod.GET)
    Response<List<ProblemDto>> index() {
        log.info("ProblemController getAll");

        List<ProblemDto> problemDtoList = problemMapper.toDtoList(problemService.getAll());

        log.info("ProblemController getAll: {}", problemDtoList);

        return Response.success(problemDtoList);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    Response<ProblemDto> show(@PathVariable Integer id) {
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

    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    Response<ProblemDto> create(@RequestBody ProblemDto problemDto) {
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

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    Response<ProblemDto> delete(@PathVariable Integer id) {
        log.info("Problem Controller delete: {}");

        problemService.delete(id);

        return Response.success();
    }
}

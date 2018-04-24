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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/problems", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProblemController {
    private static final Logger log = LoggerFactory.getLogger(ProblemController.class);

    private ProblemService problemService;
    private ProblemMapper problemMapper;

    public ProblemController(ProblemService problemService, ProblemMapper problemMapper) {
        this.problemService=problemService;
        this.problemMapper=problemMapper;
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
}

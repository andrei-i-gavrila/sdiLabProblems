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
import ro.ubb.labproblems.service.AssignmentService;
import ro.ubb.labproblems.validator.AssignmentValidator;
import ro.ubb.labproblems.validator.ValidationErrorDto;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/assignments", produces = MediaType.APPLICATION_JSON_VALUE)
public class AssignmentController {
    private static final Logger log = LoggerFactory.getLogger(AssignmentController.class);

    private AssignmentService assignmentService;
    private AssignmentMapper assignmentMapper;
    private AssignmentValidator assignmentValidator;

    public AssignmentController(AssignmentService assignmentService, AssignmentMapper assignmentMapper, AssignmentValidator assignmentValidator) {
        this.assignmentService = assignmentService;
        this.assignmentMapper = assignmentMapper;
        this.assignmentValidator = assignmentValidator;
    }

    @RequestMapping(method = RequestMethod.GET)
    Response<List<AssignmentDto>> index() {
        log.info("AssignmentController getAll");

        List<AssignmentDto> assignmentDtoList = assignmentMapper.toDtoList(assignmentService.getAll());

        log.info("AssignmentController getAll: {}", assignmentDtoList);

        return Response.success(assignmentDtoList);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
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

    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    Response<AssignmentDto> create(@RequestBody AssignmentDto assignmentDto) {
        log.info("AssignmentController create: {}", assignmentDto);

        Assignment assignment = assignmentMapper.toEntity(assignmentDto);

        Optional<List<ValidationErrorDto>> validationErrors = assignmentValidator.validate(assignment);
        if (validationErrors.isPresent()) {
            log.info("AssignmentController create errors: {}", validationErrors.get());
            return Response.fail(validationErrors.get());
        }

        assignment = assignmentService.save(assignment);
        assignmentDto = assignmentMapper.toDto(assignment);

        log.info("AssignmentController create: {}", assignmentDto);
        return Response.success(assignmentDto);
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    Response<AssignmentDto> delete(@PathVariable Integer id) {
        log.info("Assignment Controller delete: {}");

        assignmentService.delete(id);

        return Response.success();
    }
}

package ro.ubb.labproblems.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.ubb.labproblems.ErrorDto;
import ro.ubb.labproblems.Response;
import ro.ubb.labproblems.dto.StudentDto;
import ro.ubb.labproblems.mapper.StudentMapper;
import ro.ubb.labproblems.model.Student;
import ro.ubb.labproblems.service.AssignmentService;
import ro.ubb.labproblems.service.StudentService;
import ro.ubb.labproblems.validator.StudentValidator;
import ro.ubb.labproblems.validator.ValidationErrorDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    private StudentService studentService;
    private AssignmentService assignmentService;
    private StudentMapper studentMapper;
    private StudentValidator studentValidator;

    public StudentController(StudentService studentService, AssignmentService assignmentService, StudentMapper studentMapper, StudentValidator studentValidator) {
        this.studentService = studentService;
        this.assignmentService = assignmentService;
        this.studentMapper = studentMapper;
        this.studentValidator = studentValidator;
    }

    @GetMapping
    Response<List<StudentDto>> index() {
        log.info("StudentController getAll");

        List<StudentDto> studentDtoList = studentMapper.toDtoList(studentService.getAll());

        log.info("StudentController getAll: {}", studentDtoList);

        return Response.success(studentDtoList);
    }

    @GetMapping(path = "/{id}")
    Response<StudentDto> show(@PathVariable Integer id) {
        log.info("StudentController show: {}", id);

        Optional<Student> optionalStudent = studentService.get(id);

        if (!optionalStudent.isPresent()) {
            log.info("Student controller show: student not found");
            return Response.fail(new ErrorDto("Student with id {} does not exist", id));
        }

        StudentDto studentDto = studentMapper.toDto(optionalStudent.get());

        log.info("Student Controller show: {}", studentDto);

        return Response.success(studentDto);
    }

    @PostMapping
    Response<StudentDto> create(@RequestBody StudentDto studentDto) {
        log.info("StudentController create: {}", studentDto);

        Student student = studentMapper.toEntity(studentDto);

        Optional<List<ValidationErrorDto>> validationErrors = studentValidator.validate(student);
        if (validationErrors.isPresent()) {
            log.info("StudentController create errors: {}", validationErrors.get());
            return Response.fail(validationErrors.get());
        }

        student = studentService.save(student);
        studentDto = studentMapper.toDto(student);

        log.info("StudentController create: {}", studentDto);
        return Response.success(studentDto);
    }

    @DeleteMapping(path = "/{id}")
    Response<StudentDto> delete(@PathVariable Integer id) {
        log.info("Student Controller delete: {}");

        studentService.get(id).ifPresent(student -> assignmentService.deleteAllOfStudent(student));
        studentService.delete(id);

        return Response.success();
    }
}

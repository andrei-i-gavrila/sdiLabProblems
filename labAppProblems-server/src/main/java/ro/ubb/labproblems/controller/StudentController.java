package ro.ubb.labproblems.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import ro.ubb.labproblems.service.StudentService;

import java.nio.charset.Charset;

@RestController
public class StudentController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(path = "/students/create", method = RequestMethod.POST)
    String create(String name, Integer registrationNumber, Integer groupNumber) {
        log.info("StudentController create");
        String result = studentService.add(decodeString(name), registrationNumber, groupNumber);
        log.info("StudentController create: {}", result);
        return result;
    }

    @RequestMapping(path = "/students", method = RequestMethod.GET)
    String getAll() {
        log.info("StudentController getAll");
        String result = studentService.showAll();
        log.info("StudentController getAll: {}", result);
        return result;
    }

    @RequestMapping(path = "/students/{registrationNumber}/delete", method = RequestMethod.POST)
    String remove(@PathVariable Integer registrationNumber) {
        log.info("StudentController remove");
        String result = studentService.remove(registrationNumber);
        log.info("StudentController remove: {}", result);
        return result;
    }

    @RequestMapping(path = "/students/{registrationNumber}", method = RequestMethod.POST)
    String update(@PathVariable Integer registrationNumber, @RequestParam String name, Integer groupNumber) {
        log.info("StudentController update");
        String result = studentService.update(decodeString(name), registrationNumber, groupNumber);
        log.info("StudentController update: {}", result);
        return result;
    }

    @RequestMapping(path = "/students/group/{groupNumber}", method = RequestMethod.GET)
    String group(@PathVariable Integer groupNumber) {
        log.info("StudentController group");
        String result = studentService.filterByGroup(groupNumber);
        log.info("StudentController group: {}", result);
        return result;
    }

    private String decodeString(String value) {
        return UriUtils.decode(value, Charset.defaultCharset());
    }
}

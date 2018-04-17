package ro.ubb.labproblems.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import ro.ubb.labproblems.service.AssignmentService;

import java.nio.charset.Charset;

@RestController
public class AssignmentController {
    private static final Logger log = LoggerFactory.getLogger(AssignmentController.class);

    private AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @RequestMapping(path = "/assignments/create", method = RequestMethod.POST)
    String create(Integer registrationNumber, String title) {
        log.info("AssignmentController create");
        String result = assignmentService.assign(registrationNumber, decodeString(title));
        log.info("AssignmentController create: {}", result);
        return result;
    }

    @RequestMapping(path = "/assignments", method = RequestMethod.GET)
    String getAll() {
        log.info("AssignmentController getAll");
        String result = assignmentService.showAll();
        log.info("AssignmentController getAll: {}", result);
        return result;
    }

    @RequestMapping(path = "/assignments/{registrationNumber}/{title}/delete", method = RequestMethod.POST)
    String remove(@PathVariable Integer registrationNumber, @PathVariable String title) {
        log.info("AssignmentController remove");
        String result = assignmentService.unassign(decodeString(title), registrationNumber);
        log.info("AssignmentController remove: {}", result);
        return result;
    }

    @RequestMapping(path = "/assignments/{registrationNumber}/{title}", method = RequestMethod.POST)
    String update(@PathVariable Integer registrationNumber, @PathVariable String title, @RequestParam Double grade) {
        log.info("AssignmentController update");
        String result = assignmentService.grade(decodeString(title), registrationNumber, grade);
        log.info("AssignmentController update: {}", result);
        return result;
    }

    private String decodeString(String value) {
        return UriUtils.decode(value, Charset.defaultCharset());
    }
}

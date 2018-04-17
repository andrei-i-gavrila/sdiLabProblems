package ro.ubb.labproblems.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import ro.ubb.labproblems.service.ProblemService;

import java.nio.charset.Charset;

@RestController
public class ProblemController {
    private static final Logger log = LoggerFactory.getLogger(ProblemController.class);

    private ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @RequestMapping(path = "/problems/create", method = RequestMethod.POST)
    String create(String title, String description) {
        log.info("Problem Controller create");
        String result = problemService.add(decodeString(title), description);
        log.info("ProblemController create: {}", result);
        return result;
    }

    @RequestMapping(path = "/problems", method = RequestMethod.GET)
    String getAll() {
        log.info("ProblemController getAll");
        String result = problemService.showAll();
        log.info("ProblemController getAll: {}", result);
        return result;
    }

    @RequestMapping(path = "/problems/{title}/delete", method = RequestMethod.POST)
    String remove(@PathVariable String title) {
        log.info("ProblemController remove");
        String result = problemService.remove(title);
        log.info("ProblemController remove: {}", result);
        return result;
    }

    @RequestMapping(path = "/problems/{title}", method = RequestMethod.POST)
    String update(@PathVariable String title, @RequestParam String description) {
        log.info("ProblemController update");
        String result = problemService.update(decodeString(title), description);
        log.info("ProblemController update: {}", result);
        return result;
    }

    private String decodeString(String value) {
        return UriUtils.decode(value, Charset.defaultCharset());
    }
}

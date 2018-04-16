package ro.ubb.labproblems.controller;

import org.springframework.stereotype.Component;

@Component
public class ProblemRestController {
    public String showAll() {
        return "Todo";
    }

    public String add(String title, String description) {
        return "Todo";

    }

    public String remove(String title) {
        return "Todo";

    }

    public String update(String title, String description) {
        return "Todo";
    }
}

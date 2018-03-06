package ro.ubb.labproblems.ui;

import ro.ubb.labproblems.controller.ProblemController;

import java.util.Scanner;

public class ProblemsMenu extends CommandMenu {
    private final ProblemController problemController;

    public ProblemsMenu(ProblemController problemController, Scanner scanner) {
        super("Problems operations", scanner);
        this.problemController = problemController;
    }

    @Override
    protected void registerCommands() {

    }
}

package ro.ubb.labproblems.ui;

import ro.ubb.labproblems.controller.ProblemController;

import java.util.Scanner;

public class ProblemsMenu extends CommandMenu {
    private final ProblemController problemController;

    public ProblemsMenu(ProblemController problemController, Scanner scanner) {
        super("Problems operations", scanner);
        this.problemController = problemController;
        registerCommands();
    }

    @Override
    protected void registerCommands() {

        registerCommand("add", "Adds a new problem", this::addCommand);
        registerCommand("all", "Shows all problems", this::showAllCommand);
        registerCommand("remove", "Remove a problem", this::removeCommand);
        registerCommand("update", "Update a problem", this::updateCommand);
        super.registerCommands();
    }

    private void showAllCommand() {
        System.out.println(problemController.showAll() + '\n');
    }

    private void addCommand() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.println(problemController.add(title, description));
    }

    private void removeCommand() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.println(problemController.remove(title));

    }

    private void updateCommand() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.println(problemController.update(title, description));
    }
}

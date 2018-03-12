package ro.ubb.labproblems.ui;

import ro.ubb.labproblems.controller.ProblemController;

import java.util.Scanner;

/**
 * Extending {@link CommandMenu CommandMenu}, the ProblemsMenu defines the 4 keywords in the map(add, all, remove, update), and then works with the repository's problem-type entities
 */
public class ProblemsMenu extends CommandMenu {
    /**
     * {@link ProblemController Problemcontroller} to support the ProblemsMenu
     */
    private final ProblemController problemController;

    /**
     * Constructor for ProblemsMenu
     *
     * @param problemController {@link ProblemController ProblemController}-type object
     * @param scanner           {@link Scanner Scanner}
     */
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

    /**
     * Command for showing all problems in the repository
     */
    private void showAllCommand() {
        System.out.println(problemController.showAll() + '\n');
    }

    /**
     * Command for adding a problem to the repository
     */
    private void addCommand() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.println(problemController.add(title, description));
    }

    /**
     * Command for removing a problem from the repository
     */
    private void removeCommand() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.println(problemController.remove(title));

    }

    /**
     * Command for updating a problem in the repository
     */
    private void updateCommand() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.println(problemController.update(title, description));
    }
}

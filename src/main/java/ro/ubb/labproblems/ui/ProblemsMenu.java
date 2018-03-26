package ro.ubb.labproblems.ui;

import ro.ubb.labproblems.service.ProblemService;

import java.util.Scanner;

/**
 * Extending {@link CommandMenu CommandMenu}, the ProblemsMenu defines the 4 keywords in the map(add, all, remove, update), and then works with the repository's problem-type entities
 */
public class ProblemsMenu extends CommandMenu {

    /**
     * Constructor for ProblemsMenu
     *
     * @param problemController {@link ProblemService ProblemService}-type object
     * @param scanner           {@link Scanner Scanner}
     */
    public ProblemsMenu(CommandMenu parentMenu) {
        super("Problems operations", parentMenu);
    }

    @Override
    protected void registerCommands() {

        registerCommand("add", "Adds a new problem", this::addCommand);
        registerCommand("all", "Shows all problems", this::showAllCommand);
        registerCommand("remove", "Remove a problem", this::removeCommand);
        registerCommand("update", "Update a problem", this::updateCommand);
        registerCommand("mostAssigned", "Most assigned problem", this::mostAssignedProblem);

        super.registerCommands();
    }

    /**
     * Command for showing all problems in the repository
     */
    private void showAllCommand() {
        System.out.println(problemService.showAll() + '\n');
    }

    /**
     * Command for adding a problem to the repository
     */
    private void addCommand() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.println(problemService.add(title, description));
    }

    /**
     * Command for removing a problem from the repository
     */
    private void removeCommand() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.println(problemService.remove(title));

    }

    /**
     * Command for updating a problem in the repository
     */
    private void updateCommand() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("New description: ");
        String description = scanner.nextLine();

        System.out.println(problemService.update(title, description));
    }

    private void mostAssignedProblem() {
        System.out.print("The most times assigned problem is ");
        System.out.println(problemService.mostAssignedProblem());
    }
}

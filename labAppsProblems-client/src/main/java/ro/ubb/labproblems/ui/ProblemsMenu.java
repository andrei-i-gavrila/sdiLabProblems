package ro.ubb.labproblems.ui;

/**
 * Extending {@link CommandMenu CommandMenu}, the ProblemsMenu defines the 4 keywords in the map(add, all, remove, update), and then works with the ro.ubb.labproblems.repository's problem-type entities
 */
public class ProblemsMenu extends CommandMenu {

    /**
     * Constructor for ProblemsMenu
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
//        registerCommand("mostAssigned", "Most assigned problem", this::mostAssignedProblem);

        super.registerCommands();
    }

    /**
     * Command for showing all problems in the ro.ubb.labproblems.repository
     */
    private void showAllCommand() {
        printWhenDone(() -> problemController.showAll() + '\n');
    }

    /**
     * Command for adding a problem to the ro.ubb.labproblems.repository
     */
    private void addCommand() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        printWhenDone(() -> problemController.add(title, description));
    }

    /**
     * Command for removing a problem from the ro.ubb.labproblems.repository
     */
    private void removeCommand() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        printWhenDone(() -> problemController.remove(title));

    }

    /**
     * Command for updating a problem in the ro.ubb.labproblems.repository
     */
    private void updateCommand() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("New description: ");
        String description = scanner.nextLine();

        printWhenDone(() -> problemController.update(title, description));
    }

//    private void mostAssignedProblem() {
//        System.out.print("The most times assigned problem is ");
//        printWhenDone(problemController::mostAssignedProblem);
//    }
}

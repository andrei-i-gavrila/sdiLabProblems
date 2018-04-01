package ro.ubb.labproblems.ui;

/**
 * Extending {@link CommandMenu CommandMenu}, the StudentsMenu defines the 4 keywords in the map(add, all, remove, update), and then works with the repository's student-type entities
 */
public class StudentsMenu extends CommandMenu {

    public StudentsMenu(CommandMenu parentMenu) {
        super("Students operations", parentMenu);
    }

    @Override
    protected void registerCommands() {
        registerCommand("add", "Adds a new student", this::addCommand);
        registerCommand("all", "Shows all students", this::showAllCommand);
        registerCommand("remove", "Remove a student", this::removeCommand);
        registerCommand("update", "Update a student", this::updateCommand);
        registerCommand("group", "All students of a group", this::groupFilter);
        registerCommand("best", "The student having the best average grade", this::bestStudent);
        registerCommand("assignedProblems", "All problems assigned to a student", this::assignedProblems);
        super.registerCommands();
    }

    /**
     * Command for showing all the students in the repository
     */
    private void showAllCommand() {
        printWhenDone(studentService::showAll);
    }

    /**
     * Command for adding a new student into the repository
     */
    private void addCommand() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Registration number: ");
        String registrationNumber = scanner.nextLine();

        System.out.print("Group number: ");
        Integer groupNumber = Integer.parseInt(scanner.nextLine());

        printWhenDone(() -> studentService.add(name, registrationNumber, groupNumber));
    }

    /**
     * Command for removing a student from the repository
     */
    private void removeCommand() {
        System.out.print("Registration number: ");
        String registrationNumber = scanner.nextLine();

        printWhenDone(() -> studentService.remove(registrationNumber));

    }

    /**
     * Command for updating a student in the repository
     */
    private void updateCommand() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Registration number: ");
        String registrationNumber = scanner.nextLine();

        System.out.print("Group number: ");
        Integer groupNumber = scanner.nextInt();

        printWhenDone(() -> studentService.update(name, registrationNumber, groupNumber));
    }

    private void groupFilter() {
        System.out.print("Group number: ");
        Integer groupNumber = scanner.nextInt();

        printWhenDone(() -> studentService.filterByGroup(groupNumber));
    }

    private void bestStudent() {
        printWhenDone(studentService::bestStudent);
    }

    private void assignedProblems() {
        String registrationNumber = readRegistrationNumber();

        printWhenDone(() -> assignmentService.filterByStudent(registrationNumber));
    }
}

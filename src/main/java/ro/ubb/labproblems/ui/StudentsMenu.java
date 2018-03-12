package ro.ubb.labproblems.ui;

import ro.ubb.labproblems.controller.StudentController;

import java.util.Scanner;

/**
 * Extending {@link CommandMenu CommandMenu}, the StudentsMenu defines the 4 keywords in the map(add, all, remove, update), and then works with the repository's student-type entities
 */
public class StudentsMenu extends CommandMenu {

    /**
     * {@link StudentController StudentController} to support the StudentMenu
     */
    private final StudentController studentController;

    /**
     * Constructor of StudentsMenu
     *
     * @param studentController {@link StudentController StudentController}-type object
     * @param scanner           {@link Scanner Scanner}
     */
    public StudentsMenu(StudentController studentController, Scanner scanner) {
        super("Students operations", scanner);
        this.studentController = studentController;
        registerCommands();
    }

    @Override
    protected void registerCommands() {
        registerCommand("add", "Adds a new student", this::addCommand);
        registerCommand("all", "Shows all students", this::showAllCommand);
        registerCommand("remove", "Remove a student", this::removeCommand);
        registerCommand("update", "Update a student", this::updateCommand);
        super.registerCommands();
    }

    /**
     * Command for showing all the students in the repository
     */
    private void showAllCommand() {
        System.out.println(studentController.showAll());
    }

    /**
     * Command for adding a new student into the repository
     */
    private void addCommand() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Registration number: ");
        Integer registrationNumber = scanner.nextInt();

        System.out.print("Group number: ");
        Integer groupNumber = scanner.nextInt();

        System.out.println(studentController.add(name, registrationNumber, groupNumber));
    }

    /**
     * Command for removing a student from the repository
     */
    private void removeCommand() {
        System.out.print("Registration number: ");
        Integer registrationNumber = scanner.nextInt();

        System.out.println(studentController.remove(registrationNumber));

    }

    /**
     * Command for updating a student in the repository
     */
    private void updateCommand() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Registration number: ");
        Integer registrationNumber = scanner.nextInt();

        System.out.print("Group number: ");
        Integer groupNumber = scanner.nextInt();

        System.out.println(studentController.update(name, registrationNumber, groupNumber));
    }
}

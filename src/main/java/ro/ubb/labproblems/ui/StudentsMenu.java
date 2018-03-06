package ro.ubb.labproblems.ui;

import ro.ubb.labproblems.controller.StudentController;

import java.util.Scanner;

public class StudentsMenu extends CommandMenu {

    private final StudentController studentController;

    public StudentsMenu(StudentController studentController, Scanner scanner) {
        super("Students operations", scanner);
        this.studentController = studentController;
    }

    @Override
    protected void registerCommands() {
        registerCommand("add", "Adds a new student", this::addCommand);
        registerCommand("all", "Shows all students", this::showAllCommand);
    }

    private void showAllCommand() {
        System.out.println(studentController.showAll());
    }

    private void addCommand() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Registration number: ");
        Integer registrationNumber = scanner.nextInt();

        System.out.print("Group number: ");
        Integer groupNumber = scanner.nextInt();

        System.out.println(studentController.add(name, registrationNumber, groupNumber));
    }
}

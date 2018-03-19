package ro.ubb.labproblems.ui;

import ro.ubb.labproblems.controller.AssignmentController;

import java.util.Scanner;


public class AssignmentsMenu extends CommandMenu {

    private final AssignmentController assignmentController;

    public AssignmentsMenu(AssignmentController assignmentController, Scanner scanner) {
        super("Problems operations", scanner);
        this.assignmentController = assignmentController;
        registerCommands();
    }

    @Override
    protected void registerCommands() {

        registerCommand("assign", "Assign a problem to a student", this::assign);
        registerCommand("grade", "Assign a grade", this::grade);
        registerCommand("unassign", "Unassign a problem from a student", this::unassign);
        registerCommand("all", "All problems assigned to a student", this::allProblems);
        registerCommand("most", "Most times assigned problem", this::mostAssignedProblem);
        super.registerCommands();
    }

    public void assign() {
        System.out.print("Problem title: ");
        String title = scanner.nextLine();
        System.out.print("Student id: ");
        Integer registrationNumber = scanner.nextInt();

        System.out.println(assignmentController.assign(title, registrationNumber));
    }

    public void grade() {
        System.out.print("Problem title: ");
        String title = scanner.nextLine();

        System.out.print("Student id: ");
        Integer registrationNumber = scanner.nextInt();

        System.out.print("Grade: ");
        Double grade = scanner.nextDouble();

        System.out.println(assignmentController.grade(title, registrationNumber, grade));
    }

    public void unassign() {
        System.out.print("Problem title: ");
        String title = scanner.nextLine();
        System.out.print("Student id: ");
        Integer registrationNumber = scanner.nextInt();

        System.out.println(assignmentController.unassign(title, registrationNumber));
    }

    public void allProblems() {
        System.out.print("Student id: ");
        Integer registrationNumber = scanner.nextInt();

        System.out.println(assignmentController.filterByStudent(registrationNumber).toString());
    }

    public void mostAssignedProblem() {
        System.out.print("The most times assigned problem is ");
        System.out.println(assignmentController.mostAssignedProblem());

    }
}
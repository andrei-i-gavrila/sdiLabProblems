package ro.ubb.labproblems.ui;

import ro.ubb.labproblems.controller.AssignmentController;
import ro.ubb.labproblems.controller.ProblemController;
import ro.ubb.labproblems.controller.StudentController;

import java.util.Scanner;

public class AssignmentsMenu extends CommandMenu {
    private final AssignmentController assignmentController;
    private final StudentController studentController;
    private final ProblemController problemController;

    public AssignmentsMenu(AssignmentController assignmentController, StudentController studentController, ProblemController problemController, Scanner scanner) {
        super("Assign problems", scanner);

        this.assignmentController = assignmentController;
        this.studentController = studentController;
        this.problemController = problemController;

        registerCommands();
    }


    @Override
    protected void registerCommands() {

        registerCommand("assign", "Assign a problem to a student", this::assignCommand);
        registerCommand("grade", "Grade an assignment", this::gradeCommand);
        registerCommand("unassign", "Unassign a problem from a student", this::unassignCommand);
        registerCommand("show", "Shows all assignments", this::showAllCommand);
        super.registerCommands();
    }

    private void showAllCommand() {
        System.out.println(assignmentController.showAll());
    }

    private void unassignCommand() {
        Integer studentRegistrationNumber = readRegistrationNumber();
        String problemTitle = readProblemTitle();

        System.out.println(assignmentController.unnassign(problemTitle, studentRegistrationNumber));
    }

    private String readProblemTitle() {
        System.out.print("Problem title: ");
        return scanner.nextLine();
    }

    private Integer readRegistrationNumber() {
        System.out.print("Student registration number: ");
        return scanner.nextInt();
    }

    private void gradeCommand() {
        Integer studentRegistrationNumber = readRegistrationNumber();
        String problemTitle = readProblemTitle();

        System.out.print("Grade: ");
        Double grade = scanner.nextDouble();

        System.out.println(assignmentController.grade(problemTitle, studentRegistrationNumber, grade));
    }

    private void assignCommand() {
        Integer studentRegistrationNumber = readRegistrationNumber();
        String problemTitle = readProblemTitle();

        System.out.println(assignmentController.assign(problemTitle, studentRegistrationNumber));
    }
}

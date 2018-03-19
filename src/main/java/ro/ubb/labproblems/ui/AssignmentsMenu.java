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
        registerCommand("all", "All problems assigned to a student", this::allProblems);
        registerCommand("most", "Most times assigned problem", this::mostAssignedProblem);
        super.registerCommands();
    }

    private void showAllCommand() {
        System.out.println(assignmentController.showAll());
    }

    private void unassignCommand() {
        String studentRegistrationNumber = readRegistrationNumber();
        String problemTitle = readProblemTitle();

        System.out.println(assignmentController.unassign(problemTitle, studentRegistrationNumber));
    }

    private String readProblemTitle() {
        System.out.print("Problem title: ");
        return scanner.nextLine();
    }

    private String readRegistrationNumber() {
        System.out.print("Student registration number: ");
        return scanner.nextLine();
    }

    private void gradeCommand() {
        String studentRegistrationNumber = readRegistrationNumber();
        String problemTitle = readProblemTitle();

        System.out.print("Grade: ");
        Double grade = scanner.nextDouble();

        System.out.println(assignmentController.grade(problemTitle, studentRegistrationNumber, grade));
    }

    private void assignCommand() {
        String studentRegistrationNumber = readRegistrationNumber();
        String problemTitle = readProblemTitle();

        System.out.println(assignmentController.assign(problemTitle, studentRegistrationNumber));
    }

    private void allProblems() {
        String registrationNumber = readRegistrationNumber();

        System.out.println(assignmentController.filterByStudent(registrationNumber).toString());
    }

    private void mostAssignedProblem() {
        System.out.print("The most times assigned problem is ");
        System.out.println(assignmentController.mostAssignedProblem());

    }
}
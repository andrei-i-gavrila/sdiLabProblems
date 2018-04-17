package ro.ubb.labproblems.ui;

public class AssignmentsMenu extends CommandMenu {


    public AssignmentsMenu(CommandMenu parentMenu) {
        super("Assignment operations", parentMenu);
    }


    @Override
    protected void registerCommands() {
        registerCommand("assign", "Assign a problem to a student", this::assignCommand);
        registerCommand("grade", "Grade an assignment", this::gradeCommand);
        registerCommand("unassign", "Unassign a problem from a student", this::unassignCommand);
        registerCommand("showAll", "Shows all assignments", this::showAllCommand);
//        registerCommand("solvers", "List of ids of students solving a problem for a grade greater than 8", this::bestStudents);
        super.registerCommands();
    }

    private void showAllCommand() {
        printWhenDone(assignmentController::showAll);
    }

    private void unassignCommand() {
        Integer studentRegistrationNumber = readRegistrationNumber();
        String problemTitle = readProblemTitle();

        printWhenDone(() -> assignmentController.unassign(studentRegistrationNumber, problemTitle));
    }

    private void gradeCommand() {
        Integer studentRegistrationNumber = readRegistrationNumber();
        String problemTitle = readProblemTitle();

        System.out.print("Grade: ");
        Double grade = scanner.nextDouble();

        printWhenDone(() -> assignmentController.grade(studentRegistrationNumber, problemTitle, grade));
    }

    private void assignCommand() {
        Integer studentRegistrationNumber = readRegistrationNumber();
        String problemTitle = readProblemTitle();

        printWhenDone(() -> assignmentController.assign(studentRegistrationNumber, problemTitle));
    }

//    private void bestStudents() {
//        String problemTitle = readProblemTitle();
//
//        System.out.print("Min grade: ");
//        Double minGrade = scanner.nextDouble();
//
//        printWhenDone(() -> assignmentController.filterByGrade(problemTitle, minGrade));
//    }


}
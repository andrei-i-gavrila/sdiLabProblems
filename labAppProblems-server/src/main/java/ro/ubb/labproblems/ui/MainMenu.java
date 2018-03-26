package ro.ubb.labproblems.ui;

import ro.ubb.labproblems.service.*;

/**
 * The menu that comes up when the application starts. From here we can freely switch to more exact menus, and come back here from there
 */
public class MainMenu extends CommandMenu {

    /**
     * Constructor for the MainMenu
     *
     * @param studentService    {@link StudentServiceServer StudentService}-type object
     * @param problemService    {@link ProblemServiceServer ProblemService}-type object
     * @param assignmentService {@link AssignmentServiceServer AssignmentService}-type object
     */
    public MainMenu(StudentService studentService, ProblemService problemService, AssignmentService assignmentService) {
        super("", studentService, problemService, assignmentService);
    }

    @Override
    protected void registerCommands() {
        subCommands.put("students", new StudentsMenu(this));
        subCommands.put("problems", new ProblemsMenu(this));
        subCommands.put("assignments", new AssignmentsMenu(this));
        super.registerCommands();
    }
}

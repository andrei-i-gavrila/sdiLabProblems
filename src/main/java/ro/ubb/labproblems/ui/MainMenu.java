package ro.ubb.labproblems.ui;

import ro.ubb.labproblems.controller.AssignmentController;
import ro.ubb.labproblems.controller.ProblemController;
import ro.ubb.labproblems.controller.StudentController;

/**
 * The menu that comes up when the application starts. From here we can freely switch to more exact menus, and come back here from there
 */
public class MainMenu extends CommandMenu {

    /**
     * Controller for the students
     */
    private final StudentController studentController;

    /**
     * Controller for the problems
     */
    private final ProblemController problemController;
    private final AssignmentController assignmentController;

    /**
     * Constructor for the MainMenu
     *
     * @param studentController    {@link StudentController StudentController}-type object
     * @param problemController    {@link ProblemController ProblemController}-type object
     * @param assignmentController {@link AssignmentController AssignmentController}-type object
     */
    public MainMenu(StudentController studentController, ProblemController problemController, AssignmentController assignmentController) {
        super("");
        this.studentController = studentController;
        this.problemController = problemController;
        this.assignmentController = assignmentController;
        registerCommands();
    }

    @Override
    protected void registerCommands() {
        subCommands.put("students", new StudentsMenu(studentController, scanner));
        subCommands.put("problems", new ProblemsMenu(problemController, scanner));
        subCommands.put("assignments", new AssignmentsMenu(assignmentController, studentController, problemController, scanner));
        super.registerCommands();
    }
}

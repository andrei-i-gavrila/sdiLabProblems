package ro.ubb.labproblems.ui;

import ro.ubb.labproblems.controller.ProblemController;
import ro.ubb.labproblems.controller.StudentController;

public class MainMenu extends CommandMenu {

    private final StudentController studentController;
    private final ProblemController problemController;

    public MainMenu(StudentController studentController, ProblemController problemController) {
        super("");
        this.studentController = studentController;
        this.problemController = problemController;
        registerCommands();
    }

    @Override
    protected void registerCommands() {
        subCommands.put("students", new StudentsMenu(studentController, scanner));
        subCommands.put("problems", new ProblemsMenu(problemController, scanner));
        super.registerCommands();
    }
}

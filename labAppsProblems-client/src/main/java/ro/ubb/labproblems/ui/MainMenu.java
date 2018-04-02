package ro.ubb.labproblems.ui;

import org.springframework.stereotype.Component;
import ro.ubb.labproblems.service.AssignmentService;
import ro.ubb.labproblems.service.ProblemService;
import ro.ubb.labproblems.service.StudentService;

/**
 * The menu that comes up when the application starts. From here we can freely switch to more exact menus, and come back here from there
 */
@Component
public class MainMenu extends CommandMenu {

    /**
     * Constructor for the MainMenu
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
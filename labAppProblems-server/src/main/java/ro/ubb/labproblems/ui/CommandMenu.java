package ro.ubb.labproblems.ui;

import ro.ubb.labproblems.service.AssignmentService;
import ro.ubb.labproblems.service.ProblemService;
import ro.ubb.labproblems.service.StudentService;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Abstract class to manage the input. Sets up a map for commands, and then communicates with the user
 */
public abstract class CommandMenu extends Command {

    final AssignmentService assignmentService;
    final StudentService studentService;
    final ProblemService problemService;
    /**
     * Storage for commands
     */
    Map<String, Command> subCommands = new LinkedHashMap<>();

    private boolean running = false;

    public CommandMenu(String description, StudentService studentService, ProblemService problemService, AssignmentService assignmentService) {
        super(description, null);
        this.studentService = studentService;
        this.problemService = problemService;
        this.assignmentService = assignmentService;
        registerCommands();
    }

    public CommandMenu(String description, CommandMenu parentMenu) {
        super(description, null);
        this.studentService = parentMenu.studentService;
        this.problemService = parentMenu.problemService;
        this.assignmentService = parentMenu.assignmentService;

        registerCommands();
    }

    private void printSubCommands() {
        subCommands.forEach((key, command) -> System.out.println(key + ": " + command.getDescription()));
    }

    private Command readCommand() {
        System.out.print(">> ");
        String inputCommand = scanner.nextLine();
        Command command = subCommands.get(inputCommand);

        return command != null ? command : retryReadCommand();
    }

    private Command retryReadCommand() {
        System.out.println("Unknown command");

        return readCommand();
    }

    @Override
    public void run() {
        running = true;

        while (running) {
            printSubCommands();
            Command toDo = readCommand();

            try {
                toDo.run();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    protected void registerCommand(String key, String description, Runnable runnable) {
        subCommands.put(key, new Command(description, scanner, runnable));
    }

    protected void registerCommands() {
        registerCommand("exit", "Exits the application or the current menu.", () -> running = false);
    }

    protected String readProblemTitle() {
        System.out.print("Problem title: ");
        return scanner.nextLine();
    }

    protected String readRegistrationNumber() {
        System.out.print("Student registration number: ");
        return scanner.nextLine();
    }
}

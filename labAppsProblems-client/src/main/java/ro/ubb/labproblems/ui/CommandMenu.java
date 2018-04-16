package ro.ubb.labproblems.ui;

import ro.ubb.labproblems.controller.AssignmentRestController;
import ro.ubb.labproblems.controller.ProblemRestController;
import ro.ubb.labproblems.controller.StudentRestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Abstract class to manage the input. Sets up a map for commands, and then communicates with the user
 */
public abstract class CommandMenu extends Command {

    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    final AssignmentRestController assignmentController;
    final StudentRestController studentRestController;
    final ProblemRestController problemController;
    final ExecutorService executorService;
    /**
     * Storage for commands
     */
    Map<String, Command> subCommands = new LinkedHashMap<>();

    private boolean running = false;

    public CommandMenu(String description, StudentRestController studentRestController, ProblemRestController problemController, AssignmentRestController assignmentController) {
        super(description, null);
        this.studentRestController = studentRestController;
        this.problemController = problemController;
        this.assignmentController = assignmentController;
        this.executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        registerCommands();
    }

    public CommandMenu(String description, CommandMenu parentMenu) {
        super(description, null);
        this.studentRestController = parentMenu.studentRestController;
        this.problemController = parentMenu.problemController;
        this.assignmentController = parentMenu.assignmentController;
        this.executorService = parentMenu.executorService;

        registerCommands();
    }

    private void printSubCommands() {
        subCommands.forEach((key, command) -> System.out.println(key + ": " + command.getDescription()));
    }

    private Command readCommand() {
        System.out.println();
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

    protected void printWhenDone(Callable<String> request) {
        executorService.submit(() -> {
            try {
                System.out.println(request.call());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
            System.out.print(">> ");
        });
    }
}

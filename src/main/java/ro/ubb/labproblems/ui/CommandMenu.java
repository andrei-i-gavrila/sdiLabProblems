package ro.ubb.labproblems.ui;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class CommandMenu extends Command {

    protected Map<String, Command> subCommands = new LinkedHashMap<>();
    private boolean running = false;

    public CommandMenu(String description, Scanner scanner) {
        super(description, scanner, null);
    }

    public CommandMenu(String description) {
        super(description, null);
    }

    private void printSubCommands() {
        subCommands.forEach((key, description) -> System.out.println(key + ": " + description));
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
    };
}
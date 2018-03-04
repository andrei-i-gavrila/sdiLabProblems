package ro.ubb.labproblems.ui;

import java.util.Scanner;

public abstract class CommandMenu {

    private final String description;
    protected Scanner scanner;

    public CommandMenu(String description, Scanner scanner) {
        this.description = description;
        this.scanner = scanner;
    }

    public CommandMenu(String description) {
        this(description, new Scanner(System.in));
    }


    public String getDescription() {
        return description;
    }

    public abstract void execute();


}

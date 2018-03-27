package ro.ubb.labproblems.ui;

import ro.ubb.labproblems.utils.Guards;

import java.util.Scanner;

public class Command implements Runnable {

    protected String description;
    protected Scanner scanner;
    private Runnable runnable;

    public Command(String description, Runnable runnable) {
        this.description = description;
        this.scanner = new Scanner(System.in);
        this.runnable = runnable;
    }

    public Command(String description, Scanner scanner, Runnable runnable) {
        this.description = description;
        this.scanner = scanner;
        this.runnable = runnable;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void run() {
        Guards.nullGuard(runnable);
        runnable.run();
    }

    @Override
    public String toString() {
        return description;
    }
}

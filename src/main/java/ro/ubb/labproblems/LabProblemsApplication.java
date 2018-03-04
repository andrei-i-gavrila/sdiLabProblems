package ro.ubb.labproblems;

import ro.ubb.labproblems.ui.CommandMenu;

public class LabProblemsApplication {

    public static void main(String... args) {

        CommandMenu a = new CommandMenu("asd") {

            @Override
            public void execute() {

            }
        };
    }
}

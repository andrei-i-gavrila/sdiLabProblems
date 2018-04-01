package ro.ubb.labproblems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ro.ubb.labproblems.ui.MainMenu;


@SpringBootApplication
public class LabProblemsClient {

    public static void main(String... args) {
        ApplicationContext applicationContext = SpringApplication.run(LabProblemsClient.class, args);

        MainMenu mainMenu = (MainMenu) applicationContext.getBean("mainMenu");

        mainMenu.run();
    }
}

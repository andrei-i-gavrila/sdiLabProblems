package ro.ubb.labproblems;

import ro.ubb.labproblems.service.*;
import ro.ubb.labproblems.tcp.TCPClient;
import ro.ubb.labproblems.ui.MainMenu;

public class LabProblemsClient {

    public static void main(String... args) {

        TCPClient client = new TCPClient("localhost", 1234);

        StudentService studentService = new StudentServiceClient(client);
        ProblemService problemService = new ProblemServiceClient(client);
        AssignmentService assignmentService = new AssignmentServiceClient(client);

        new MainMenu(studentService, problemService, assignmentService).run();
    }
}

package ro.ubb.labproblems.service;


import ro.ubb.labproblems.tcp.Request;
import ro.ubb.labproblems.tcp.TCPClient;

public class AssignmentServiceClient implements AssignmentService {
    private TCPClient client;

    public AssignmentServiceClient(TCPClient client) {
        this.client = client;
    }

    @Override
    public String assign(String problemTitle, String studentRegistrationNumber) {
        return client.sendThenReceive(new Request("assignment", "assign", problemTitle, studentRegistrationNumber)).getData();
    }

    @Override
    public String grade(String problemTitle, String studentRegistrationNumber, Double grade) {
        return client.sendThenReceive(new Request("assignment", "grade", problemTitle, studentRegistrationNumber, grade)).getData();

    }

    @Override
    public String unassign(String problemTitle, String studentRegistrationNumber) {
        return client.sendThenReceive(new Request("assignment", "unassign", problemTitle, studentRegistrationNumber)).getData();

    }

    @Override
    public String filterByStudent(String registrationNumber) {
        return client.sendThenReceive(new Request("assignment", "filterByStudent", registrationNumber)).getData();

    }

    @Override
    public String filterByGrade(String problemTitle, Double grade) {
        return client.sendThenReceive(new Request("assignment", "filterByGrade", problemTitle, grade)).getData();

    }

    @Override
    public String showAll() {
        return client.sendThenReceive(new Request("assignment", "showAll")).getData();
    }
}

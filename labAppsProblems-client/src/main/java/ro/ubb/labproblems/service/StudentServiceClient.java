package ro.ubb.labproblems.service;

import ro.ubb.labproblems.tcp.Request;
import ro.ubb.labproblems.tcp.TCPClient;

public class StudentServiceClient implements StudentService {

    private TCPClient client;

    public StudentServiceClient(TCPClient client) {
        this.client = client;
    }

    @Override
    public String add(String name, String registrationNumber, Integer groupNumber) {
        return client.sendThenReceive(new Request("student", "add", name, registrationNumber, groupNumber)).getData();
    }

    @Override
    public String remove(String registrationNumber) {
        return client.sendThenReceive(new Request("student", "remove", registrationNumber)).getData();
    }

    @Override
    public String update(String name, String registrationNumber, Integer groupNumber) {
        return client.sendThenReceive(new Request("student", "update", name, registrationNumber, groupNumber)).getData();
    }

    @Override
    public String showAll() {
        return client.sendThenReceive(new Request("student", "showAll")).getData();
    }

    @Override
    public String filterByGroup(Integer groupNumber) {
        return client.sendThenReceive(new Request("student", "filterByGroup", groupNumber)).getData();
    }

    @Override
    public String bestStudent() {
        return client.sendThenReceive(new Request("student", "bestStudent")).getData();
    }

    @Override
    public String getStudentAverageGrade(String studentRegistrationNumber) {
        return client.sendThenReceive(new Request("student", "getStudentAverageGrade", studentRegistrationNumber)).getData();
    }
}

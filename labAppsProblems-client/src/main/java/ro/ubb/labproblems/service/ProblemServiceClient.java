package ro.ubb.labproblems.service;

import org.springframework.stereotype.Service;
import ro.ubb.labproblems.tcp.Request;
import ro.ubb.labproblems.tcp.TCPClient;

@Service
public class ProblemServiceClient implements ProblemService {
    private TCPClient client;

    public ProblemServiceClient(TCPClient client) {
        this.client = client;
    }

    @Override
    public String add(String title, String description) {
        return client.sendThenReceive(new Request("problem", "add", title, description)).getData();
    }

    @Override
    public String remove(String title) {
        return client.sendThenReceive(new Request("problem", "remove", title)).getData();

    }

    @Override
    public String update(String title, String description) {
        return client.sendThenReceive(new Request("problem", "update", title, description)).getData();
    }

    @Override
    public String showAll() {
        return client.sendThenReceive(new Request("problem", "showAll")).getData();
    }

    @Override
    public String mostAssignedProblem() {
        return client.sendThenReceive(new Request("problem", "mostAssignedProblem")).getData();
    }
}

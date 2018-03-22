package ro.ubb.labproblems.repository;

import ro.ubb.labproblems.domain.entities.Assignment;
import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private List<Student> students;
    private List<Problem> problems;
    private List<Assignment> assignments;

    public Storage() {
        students = new ArrayList<>();
        problems = new ArrayList<>();
        assignments = new ArrayList<>();
    }

    public <T> List<T> getList(Class<T> type) {
        switch (type) {
            case Student.class: return (List<T>) students;
        }
    }
}

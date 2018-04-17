package ro.ubb.labproblems.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class AssignmentId implements Serializable {

    @JoinColumn(name = "problem_id")
    @ManyToOne
    private Problem problem;

    @JoinColumn(name = "student_id")
    @ManyToOne
    private Student student;

    public AssignmentId() {
    }

    public AssignmentId(Problem problem, Student student) {
        this.problem = problem;
        this.student = student;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

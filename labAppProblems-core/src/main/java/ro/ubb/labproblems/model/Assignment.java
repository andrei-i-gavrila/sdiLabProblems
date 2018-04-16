package ro.ubb.labproblems.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Assignment implements Serializable {


    @JoinColumn(name = "problem_id")
    @ManyToOne
    @Id
    private Problem problem;

    @JoinColumn(name = "student_id")
    @ManyToOne
    @Id
    private Student student;

    private Double grade;

    public Assignment() {
    }

    public Assignment(Problem problem, Student student, Double grade) {
        this.problem = problem;
        this.student = student;
        this.grade = grade;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return student.getName() + " --- " + problem.getTitle() + ": " + grade.toString();
    }
}

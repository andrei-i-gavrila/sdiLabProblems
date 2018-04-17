package ro.ubb.labproblems.model;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Assignment implements Serializable {


    @EmbeddedId
    private AssignmentId assignmentId;

    public Assignment(Problem problem, Student student, Double grade) {
        this.assignmentId = new AssignmentId(problem, student);
        this.grade = grade;
    }

    public AssignmentId getAssignmentId() {
        return assignmentId;
    }

    private Double grade;

    public Assignment() {
    }

    public void setAssignmentId(AssignmentId assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Problem getProblem() {
        return assignmentId.getProblem();
    }

    public void setProblem(Problem problem) {
        this.assignmentId.setProblem(problem);
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return assignmentId.getStudent();
    }

    public void setStudent(Student student) {
        this.assignmentId.setStudent(student);
    }

    @Override
    public String toString() {
        return assignmentId.getStudent().getName() + " --- " + assignmentId.getProblem().getTitle() + ": " + (grade == null? "Not graded" : grade.toString());
    }
}

package ro.ubb.labproblems.domain.entities;

import java.util.Objects;

public class Assignment implements BaseEntity<Integer> {

    private final String problemTitle;
    private final Integer studentRegistrationNumber;
    private Double grade;

    public Assignment(String problemTitle, Integer studentRegistrationNumber) {
        this.problemTitle = problemTitle;
        this.studentRegistrationNumber = studentRegistrationNumber;
        this.grade = null;
    }

    public Integer getStudentRegistrationNumber() {
        return studentRegistrationNumber;
    }

    public String getProblemTitle() {
        return problemTitle;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public Integer getIdentifier() {
        return Objects.hash(studentRegistrationNumber, problemTitle);
    }

    @Override
    public String toString() {
        return studentRegistrationNumber.toString() + " --- " + problemTitle + " --- " +
                (grade == null ? "Not graded" : grade.toString());
    }
}

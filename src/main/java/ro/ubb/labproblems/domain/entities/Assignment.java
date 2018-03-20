package ro.ubb.labproblems.domain.entities;

public class Assignment extends BaseEntity<String> {

    private final String problemTitle;
    private final String studentRegistrationNumber;
    private Double grade;

    public Assignment(String problemTitle, String studentRegistrationNumber) {
        this.problemTitle = problemTitle;
        this.studentRegistrationNumber = studentRegistrationNumber;
        this.grade = null;
    }

    public String getStudentRegistrationNumber() {
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
    public String getIdentifier() {
        return studentRegistrationNumber + "#" + problemTitle;
    }

    @Override
    public String toString() {
        return studentRegistrationNumber + " --- " + problemTitle + " --- " +
                (grade == null ? "Not graded" : grade.toString());
    }
}

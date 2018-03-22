package ro.ubb.labproblems.domain.entities;

public class Assignment extends BaseEntity<String> {

    private String problemTitle;
    private String studentRegistrationNumber;
    private Double grade;
    public Assignment() {
    }
    public Assignment(String problemTitle, String studentRegistrationNumber) {
        this.problemTitle = problemTitle;
        this.studentRegistrationNumber = studentRegistrationNumber;
        this.grade = null;
    }

    public String getStudentRegistrationNumber() {
        return studentRegistrationNumber;
    }

    public void setStudentRegistrationNumber(String studentRegistrationNumber) {
        this.studentRegistrationNumber = studentRegistrationNumber;
    }

    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
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

package ro.ubb.labproblems.domain.entities;

public class Student implements BaseEntity<Integer> {

    private Integer registrationNumber;
    private String name;
    private Integer groupNumber;


    public Student(Integer registrationNumber, String name, Integer groupNumber) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.groupNumber = groupNumber;
    }

    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    @Override
    public Integer getIdentifier() {
        return registrationNumber;
    }

    @Override
    public String toString() {
        return name + " " + registrationNumber.toString() + " " + groupNumber.toString();
    }
}

package ro.ubb.labproblems.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Implements the student entity as a BaseEntity
 */
public class Student extends BaseEntity<String> {

    private String registrationNumber;
    private String name;
    private Integer groupNumber;


    public Student() {
    }

    /**
     * Constructor for a student entity
     *
     * @param registrationNumber Unique registration number for the student
     * @param name               The student's name
     * @param groupNumber        The student's group number
     */
    public Student(String registrationNumber, String name, Integer groupNumber) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.groupNumber = groupNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
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

    @JsonIgnore
    @Override
    public String getIdentifier() {
        return registrationNumber;
    }

    @Override
    public String toString() {
        return name + " " + registrationNumber + " " + groupNumber.toString();
    }
}

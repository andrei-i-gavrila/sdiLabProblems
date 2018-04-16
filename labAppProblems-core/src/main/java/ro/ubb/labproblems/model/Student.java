package ro.ubb.labproblems.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;


@Entity
public class Student {

    @Id
    @NotNull
    @Digits(integer = 4, fraction = 0)
    private Integer registrationNumber;

    @NotNull
    @NotBlank
    private String name;

    @Digits(integer = 3, fraction = 3, message = "Group number has to be 3 digits")
    @NotNull
    private Integer groupNumber;


    public Student() {
    }

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
    public String toString() {
        return name + " " + registrationNumber.toString() + " " + groupNumber.toString();
    }
}

package ro.ubb.labproblems.controller;

import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.utils.IteratorUtils;

import java.util.stream.Collectors;

public class StudentController {

    private final Repository<Integer, Student> studentRepository;

    public StudentController(Repository<Integer, Student> studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String add(String name, Integer registrationNumber, Integer groupNumber) {
        try {
            return studentRepository.save(new Student(registrationNumber, name, groupNumber))
                    .map(student -> "A student with such registration number already exists")
                    .orElse("Student added successfully");
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    public String remove(Integer registrationNumber) {
        return studentRepository.delete(registrationNumber)
                .map(student -> "Student removed successfully")
                .orElse("No student with such registration ID was found");
    }

    public String update(String name, Integer registrationNumber, Integer groupNumber) {
        try {
            return studentRepository.update(new Student(registrationNumber, name, groupNumber))
                    .map(student -> "No student was found with the given registration number")
                    .orElse("Student updated successfully");
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    public String showAll() {
        return IteratorUtils
                .stream(studentRepository.findAll())
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}

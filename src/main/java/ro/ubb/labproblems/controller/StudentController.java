package ro.ubb.labproblems.controller;

import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.utils.IteratorUtils;

import java.util.Optional;
import java.util.stream.Collectors;

public class StudentController {

    private final Repository<Integer, Student> studentRepository;

    public StudentController(Repository<Integer, Student> studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String add(String name, Integer registrationNumner, Integer groupNumber) {
        try {
            if (studentRepository.save(new Student(registrationNumner, name, groupNumber)).equals(Optional.empty()))
                return "Student added successfully";
            else
                return "A student with such registration number already exists";
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    public String remove(Integer registrationNumber) {
        if (studentRepository.delete(registrationNumber).equals(Optional.empty()))
            return "No student with such registration ID was found";
        else
            return "Student removed successfully";

    }

    public String update(String name, Integer registrationNumner, Integer groupNumber) {
        try {
            if (studentRepository.update(new Student(registrationNumner, name, groupNumber)).equals(Optional.empty()))
                return "Student updated successfully";
            else
                return "No student was found with the given registration number";
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

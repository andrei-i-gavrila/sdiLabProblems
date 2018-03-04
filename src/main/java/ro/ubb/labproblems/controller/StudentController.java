package ro.ubb.labproblems.controller;

import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.utils.IteratorUtils;

import java.util.stream.Collectors;

public class StudentController {

    private Repository<Integer, Student> studentRepository;

    public StudentController(Repository<Integer, Student> studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String add(String name, Integer registrationNumner, Integer groupNumber) {
        try {
            studentRepository.save(new Student(registrationNumner, name, groupNumber));
            return "Student added successfully";
        } catch (ValidatorException e) {
            return e.getMessage();
        }
    }

    public String showAll() {
        return IteratorUtils.stream(studentRepository.findAll()).map(Object::toString).collect(Collectors.joining("\n"));
    }
}

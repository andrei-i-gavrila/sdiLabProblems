package ro.ubb.labproblems.service;

import org.springframework.stereotype.Service;
import ro.ubb.labproblems.model.Student;
import ro.ubb.labproblems.repository.AssignmentRepository;
import ro.ubb.labproblems.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;

    public StudentService(StudentRepository studentRepository, AssignmentRepository assignmentRepository) {
        this.studentRepository = studentRepository;
        this.assignmentRepository = assignmentRepository;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> get(Integer id) {
        return studentRepository.findById(id);
    }

    public List<Student> filterByGroup(Integer groupNumber) {
        return studentRepository.findAllByGroupNumber(groupNumber);
    }

}

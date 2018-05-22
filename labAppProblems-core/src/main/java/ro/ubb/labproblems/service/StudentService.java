package ro.ubb.labproblems.service;

import org.springframework.stereotype.Service;
import ro.ubb.labproblems.model.Student;
import ro.ubb.labproblems.repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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

    public List<Student> filterByName(String nameFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> studentCriteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = studentCriteriaQuery.from(Student.class);
        Predicate filterPredicate = criteriaBuilder.like(root.get("name"), "%" + nameFilter + "%");
        studentCriteriaQuery.where(filterPredicate);

        return entityManager.createQuery(studentCriteriaQuery).getResultList();
    }
}

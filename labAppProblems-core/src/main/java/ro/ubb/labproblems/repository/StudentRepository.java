package ro.ubb.labproblems.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.labproblems.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}

package ro.ubb.labproblems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.labproblems.model.Assignment;
import ro.ubb.labproblems.model.Problem;
import ro.ubb.labproblems.model.Student;


@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {

    @Transactional
    void deleteByStudent(Student student);

    @Transactional
    void deleteByProblem(Problem problem);

}

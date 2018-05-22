package ro.ubb.labproblems.repository;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.labproblems.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByGroupNumber(Integer groupNumber);

//    @Query(value = "select distinct s from Student s join fetch s.assignments a")
    @EntityGraph(value = "studentWithAssignments", type = EntityGraph.EntityGraphType.FETCH)
    List<Student> findAll();

    //    @Query("select distinct s from Student s where name like %:name%")
    @EntityGraph(value = "studentWithAssignments", type = EntityGraph.EntityGraphType.FETCH)
    List<Student> findAllByNameContaining(String name);
}

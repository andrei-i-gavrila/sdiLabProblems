package ro.ubb.labproblems.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.labproblems.model.Problem;

import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Integer> {

    @EntityGraph(value = "problemWithAssignments", type = EntityGraph.EntityGraphType.FETCH)
    List<Problem> findAll();

}

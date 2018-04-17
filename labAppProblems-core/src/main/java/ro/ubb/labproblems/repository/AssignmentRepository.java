package ro.ubb.labproblems.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.labproblems.model.Assignment;
import ro.ubb.labproblems.model.AssignmentId;

@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, AssignmentId> {
}

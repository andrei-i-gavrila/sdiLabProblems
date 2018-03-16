package ro.ubb.labproblems.domain.validators;

import ro.ubb.labproblems.domain.entities.Assignment;
import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.entities.Student;
import ro.ubb.labproblems.repository.Repository;

import static ro.ubb.labproblems.utils.Guards.nullGuard;

public class AssignmentValidator implements Validator<Assignment> {

    private Repository<Integer, Student> studentRepository;
    private Repository<String, Problem> problemRepository;

    public AssignmentValidator(Repository<Integer, Student> studentRepository, Repository<String, Problem> problemRepository) {
        this.studentRepository = studentRepository;
        this.problemRepository = problemRepository;
    }

    @Override
    public void validate(Assignment entity) throws ValidatorException {
        nullGuard(entity);

        if (!studentRepository.find(entity.getStudentRegistrationNumber()).isPresent()) {
            throw new ValidatorException("Registration number does not exist", entity);
        }

        if (!problemRepository.find(entity.getProblemTitle()).isPresent()) {
            throw new ValidatorException("Problem title does not exist", entity);
        }

        if (entity.getGrade() != null && entity.getGrade() < 0.0) {
            throw new ValidatorException("Grade cannot be negative", entity);
        }
    }
}

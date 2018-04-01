package ro.ubb.labproblems.repository.sql;

import org.springframework.stereotype.Component;
import ro.ubb.labproblems.domain.entities.Assignment;

import java.util.Arrays;

@Component
public class AssignmentSqlHandler implements SqlHandler<Assignment> {

    public String getInsertStatement() {
        return "insert into assignments values (?, ?, ?)";
    }

    public String getUpdateStatement() {
        return "update assignments set problemTitle = ?, studentRegistrationNumber = ?, grade = ? where concat(studentRegistrationNumber, '#', problemTitle) = ?";
    }

    public String getDeleteStatement() {
        return "delete from assignments where concat(studentRegistrationNumber, '#', problemTitle) = ?";
    }

    public String getSelectStatement() {
        return "select * from assignments";
    }

    public String getFindStatement() {
        return "select * from assignments where concat(studentRegistrationNumber, '#', problemTitle) = ?";
    }

    public Object[] getParams(Assignment assignment) {
        return Arrays.asList(assignment.getProblemTitle(), assignment.getStudentRegistrationNumber(), assignment.getGrade()).toArray();
    }
}

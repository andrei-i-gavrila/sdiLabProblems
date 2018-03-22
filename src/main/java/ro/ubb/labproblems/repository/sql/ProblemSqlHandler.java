package ro.ubb.labproblems.repository.sql;

import ro.ubb.labproblems.domain.entities.Problem;
import ro.ubb.labproblems.domain.entities.Student;

import java.util.Arrays;

public class ProblemSqlHandler implements SqlHandler<Problem>
{
    public String getInsertStatement() {
        return "insert into problems values (?, ?)";
    }

    public String getUpdateStatement() {
        return "update problems set title = ?, description = ? where title = ?";
    }

    public String getDeleteStatement() {
        return "delete from problems where where title = ?";
    }

    public String getSelectStatement() {
        return "select * from problems";
    }

    public String getFindStatement() {
        return "select * from problems where title = ?";
    }

    public Object[] getParams(Problem problem) {
        return Arrays.asList(problem.getTitle(), problem.getDescription()).toArray();
    }
}
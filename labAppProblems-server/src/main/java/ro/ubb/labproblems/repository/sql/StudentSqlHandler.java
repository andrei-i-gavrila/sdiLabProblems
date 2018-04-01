package ro.ubb.labproblems.repository.sql;

import org.springframework.stereotype.Component;
import ro.ubb.labproblems.domain.entities.Student;

import java.util.Arrays;

@Component
public class StudentSqlHandler implements SqlHandler<Student> {
    public String getInsertStatement() {
        return "insert into students values (?, ?, ?)";
    }

    public String getUpdateStatement() {
        return "update students set registrationNumber = ?, name = ?, groupNumber = ? where registrationNumber = ?";
    }

    public String getDeleteStatement() {
        return "delete from students where where registrationNumber = ?";
    }

    public String getSelectStatement() {
        return "select * from students";
    }

    public String getFindStatement() {
        return "select * from students where registrationNumber = ?";
    }

    public Object[] getParams(Student student) {
        return Arrays.asList(student.getRegistrationNumber(), student.getName(), student.getGroupNumber()).toArray();
    }
}

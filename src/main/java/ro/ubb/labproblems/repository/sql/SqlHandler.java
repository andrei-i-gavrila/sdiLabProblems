package ro.ubb.labproblems.repository.sql;

public interface SqlHandler<T> {

    String getInsertStatement();

    String getUpdateStatement();

    String getDeleteStatement();

    String getSelectStatement();

    String getFindStatement();

    Object[] getParams(T t);

}

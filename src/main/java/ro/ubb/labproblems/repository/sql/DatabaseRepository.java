package ro.ubb.labproblems.repository.sql;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import ro.ubb.labproblems.domain.entities.BaseEntity;
import ro.ubb.labproblems.domain.validators.Validator;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.repository.Repository;
import ro.ubb.labproblems.repository.sql.SqlHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ro.ubb.labproblems.utils.Guards.nullGuard;

public class DatabaseRepository<T extends BaseEntity<String>> implements Repository<String, T> {

    private final Validator<T> validator;
    private final SqlHandler<T> handler;
    private final ResultSetHandler<List<T>> resultHandler;
    private final Connection connection;
    private final QueryRunner queryRunner;

    public DatabaseRepository(Validator<T> validator, Connection connection, SqlHandler<T> handler, Class<T> type) {
        this.validator = validator;
        this.handler = handler;
        this.resultHandler = new BeanListHandler<>(type);
        this.connection = connection;
        queryRunner = new QueryRunner();
    }

    public Optional<T> find(String s) {
        nullGuard(s);
        try {
            return queryRunner.query(connection, handler.getFindStatement(), resultHandler, s).stream().findFirst();
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    public Iterable<T> findAll() {
        try {
            return queryRunner.query(connection, handler.getSelectStatement(), resultHandler);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public Optional<T> save(T entity) throws ValidatorException {
        nullGuard(entity);
        validator.validate(entity);

        try {
            return queryRunner.update(connection, handler.getInsertStatement(), handler.getParams(entity)) == 1 ? Optional.empty() : Optional.of(entity);
        } catch (SQLException e) {
            return Optional.of(entity);
        }
    }

    public Optional<T> delete(String s) {
        nullGuard(s);
        Optional<T> entity = find(s);
        if (entity.isPresent()) {
            try {
                return queryRunner.update(connection, handler.getDeleteStatement(), s) == 1 ? entity : Optional.empty();
            } catch (SQLException e) {
                return entity;
            }
        }
        return entity;
    }

    public Optional<T> update(T entity) throws ValidatorException {
        nullGuard(entity);
        validator.validate(entity);

        try {
            return queryRunner.update(connection, handler.getUpdateStatement(), handler.getParams(entity), entity.getIdentifier()) == 1 ? Optional.empty() : Optional.of(entity);
        } catch (SQLException e) {
            return Optional.of(entity);
        }
    }
}

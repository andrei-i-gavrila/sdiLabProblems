package ro.ubb.labproblems.repository.sql;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import ro.ubb.labproblems.domain.entities.BaseEntity;
import ro.ubb.labproblems.domain.validators.Validator;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.repository.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static ro.ubb.labproblems.utils.Guards.nullGuard;

public class DatabaseRepository<T extends BaseEntity<String>> implements Repository<String, T> {

    private final Validator<T> validator;
    private final SqlHandler<T> handler;
    private final JdbcOperations jdbcOperations;
    private Class<T> type;

    public DatabaseRepository(Validator<T> validator, SqlHandler<T> handler, Class<T> type, JdbcOperations jdbcOperations) {
        this.validator = validator;
        this.handler = handler;
        this.type = type;
        this.jdbcOperations = jdbcOperations;
    }

    public Optional<T> find(String s) {
        nullGuard(s);
        return jdbcOperations.query(handler.getFindStatement(), new BeanPropertyRowMapper<>(type), s).stream().findFirst();
    }

    public Iterable<T> findAll() {
        return jdbcOperations.query(handler.getSelectStatement(), new BeanPropertyRowMapper<>(type));
    }

    public Optional<T> save(T entity) throws ValidatorException {
        nullGuard(entity);
        validator.validate(entity);

        return jdbcOperations.update(handler.getInsertStatement(), handler.getParams(entity)) == 1 ? Optional.empty() : Optional.of(entity);
    }

    public Optional<T> delete(String s) {
        nullGuard(s);
        Optional<T> entity = find(s);
        return entity.map(e -> jdbcOperations.update(handler.getDeleteStatement(), s) == 1 ? e : null);
    }

    public Optional<T> update(T entity) throws ValidatorException {
        nullGuard(entity);
        validator.validate(entity);

        List<Object> arguments = Arrays.asList(handler.getParams(entity));
        arguments.add(entity.getIdentifier());

        return jdbcOperations.update(handler.getUpdateStatement(), arguments.toArray()) == 1 ? Optional.empty() : Optional.of(entity);
    }
}

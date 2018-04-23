package ro.ubb.labproblems.validator;

import java.util.List;
import java.util.Optional;

public interface Validator<T> {

    Optional<List<ValidationErrorDto>> validate(T t);

}

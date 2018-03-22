package ro.ubb.labproblems.domain.validators;

/**
 * Throwable ValidatorException class, extending the {@link Exception Exception} class
 */
public class ValidatorException extends Exception {

    private final Object invalidObject;


    public ValidatorException(String message, Object invalidObject) {
        super(message);
        this.invalidObject = invalidObject;
    }

    public Object getInvalidObject() {
        return invalidObject;
    }
}

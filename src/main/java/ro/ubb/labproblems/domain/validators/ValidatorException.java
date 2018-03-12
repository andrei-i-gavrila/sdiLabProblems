package ro.ubb.labproblems.domain.validators;

public class ValidatorException extends Exception {

    private Object invalidObject;


    public ValidatorException(String message, Object invalidObject) {
        super(message);
        this.invalidObject = invalidObject;
    }

    public Object getInvalidObject() {
        return invalidObject;
    }
}

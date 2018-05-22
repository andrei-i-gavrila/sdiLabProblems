package ro.ubb.labproblems.controller;

import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ro.ubb.labproblems.ErrorDto;
import ro.ubb.labproblems.Response;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
@EnableWebMvc
public class ErrorHandlerController {


    @ExceptionHandler({TransactionSystemException.class})
    @ResponseBody
    public <T> Response<T> handleConstraintErrors(HttpServletRequest request, TransactionSystemException exception) {
        ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception.getRootCause();
        return Response.fail(
                constraintViolationException.getConstraintViolations()
                        .stream()
                        .map(constraintViolation -> new ErrorDto(constraintViolation.getMessage()))
                        .collect(Collectors.toList())
        );
    }
}

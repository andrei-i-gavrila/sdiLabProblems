package ro.ubb.labproblems;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.helpers.MessageFormatter;

@Getter
@Setter
public class ErrorDto {

    private String message;


    public ErrorDto(String message, Object... args) {
        this.message = MessageFormatter.arrayFormat(message, args).getMessage();
    }
}

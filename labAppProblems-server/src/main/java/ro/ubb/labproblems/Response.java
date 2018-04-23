package ro.ubb.labproblems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class Response<T> {
    private boolean success;
    private T data;
    private List<? extends ErrorDto> errors;


    public static <T> Response<T> success() {
        return new Response<>(true, null, null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(true, data, null);
    }


    public static <T> Response<T> fail(List<? extends ErrorDto> errors) {
        return new Response<>(false, null, errors);
    }

    public static <T> Response<T> fail(ErrorDto error) {
        return new Response<>(false, null, Collections.singletonList(error));
    }

}

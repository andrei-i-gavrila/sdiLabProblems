package ro.ubb.labproblems.utils;

import java.util.Optional;

public class Guards {

    public static void nullGuard(Object object) {
        //noinspection ResultOfMethodCallIgnored
        Optional.ofNullable(object).orElseThrow(IllegalArgumentException::new);
    }
}

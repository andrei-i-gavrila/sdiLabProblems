package ro.ubb.labproblems.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class IteratorUtils {
    public static <T> long size(Iterable<T> iterable) {
        return parallelStream(iterable).count();
    }

    public static <T> List<T> toList(Iterable<T> iterable) {
        return stream(iterable).collect(Collectors.toList());
    }

    private static <T> Stream<T> stream(Iterable<T> iterable, boolean parallel) {
        return StreamSupport.stream(iterable.spliterator(), parallel);
    }

    public static <T> Stream<T> stream(Iterable<T> iterable) {
        return stream(iterable, false);
    }

    public static <T> Stream<T> parallelStream(Iterable<T> iterable) {
        return stream(iterable, true);
    }

    public static <T> boolean empty(Iterable<T> iterable) {
        return size(iterable) == 0;
    }


}

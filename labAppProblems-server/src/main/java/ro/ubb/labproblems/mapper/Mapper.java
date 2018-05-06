package ro.ubb.labproblems.mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sandy on 5/6/2018.
 */
public interface Mapper<D, E> {
    D toDto(E entity);

    E toEntity(D dto);

    default List<D> toDtoList(List<E> entities) {
        return entities.stream().map((e) -> toDto(e)).collect(Collectors.toList());
    }
}
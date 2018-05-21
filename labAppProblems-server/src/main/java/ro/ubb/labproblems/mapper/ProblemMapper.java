package ro.ubb.labproblems.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import ro.ubb.labproblems.dto.ProblemDto;
import ro.ubb.labproblems.model.Problem;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ProblemMapper {

    ProblemDto toDto(Problem problem);

    List<ProblemDto> toDtoList(List<Problem> problems);

    Problem toEntity(ProblemDto problemDto);

    Problem updateToEntity(ProblemDto problemDto, @MappingTarget Problem problem);
}

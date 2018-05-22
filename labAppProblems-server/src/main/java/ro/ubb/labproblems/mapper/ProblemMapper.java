package ro.ubb.labproblems.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import ro.ubb.labproblems.dto.ProblemDto;
import ro.ubb.labproblems.model.Problem;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ProblemMapper {

    ProblemDto toDto(Problem problem);

    List<ProblemDto> toDtoList(List<Problem> problems);

    @Mappings(@Mapping(target = "assignments", ignore = true))
    Problem toEntity(ProblemDto problemDto);

    @Mappings(@Mapping(target = "assignments", ignore = true))
    Problem updateToEntity(ProblemDto problemDto, @MappingTarget Problem problem);
}

package ro.ubb.labproblems.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import ro.ubb.labproblems.dto.AssignmentDto;
import ro.ubb.labproblems.model.Assignment;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = {StudentMapper.class, ProblemMapper.class})
public interface AssignmentMapper {

    @Mappings({
            @Mapping(target = "studentDto", source = "student"),
            @Mapping(target = "problemDto", source = "problem")
    })
    AssignmentDto toDto(Assignment assignment);

    List<AssignmentDto> toDtoList(List<Assignment> assignments);

    @Mappings({
            @Mapping(target = "student", ignore = true),
            @Mapping(target = "problem", ignore = true)
    })
    Assignment toEntity(AssignmentDto assignmentDto);

}

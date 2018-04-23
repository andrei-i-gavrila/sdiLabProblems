package ro.ubb.labproblems.mapper;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ro.ubb.labproblems.dto.AssignmentDto;
import ro.ubb.labproblems.model.Assignment;

import java.util.List;

@Component
@Mapper(componentModel = "spring", uses = {StudentMapper.class, ProblemMapper.class})
public interface AssignmentMapper {

    AssignmentDto toDto(Assignment assignment);

    List<AssignmentDto> toDtoList(List<Assignment> assignments);

    Assignment toEntity(AssignmentDto assignmentDto);

}

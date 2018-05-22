package ro.ubb.labproblems.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import ro.ubb.labproblems.dto.StudentDto;
import ro.ubb.labproblems.model.Student;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDto toDto(Student student);
    List<StudentDto> toDtoList(List<Student> students);

    @Mappings(@Mapping(target = "assignments", ignore = true))
    Student toEntity(StudentDto studentDto);
    
    @Mappings(@Mapping(target = "assignments", ignore = true))
    Student updateToEntity(StudentDto studentDto, @MappingTarget Student student);

}

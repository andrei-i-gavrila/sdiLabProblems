package ro.ubb.labproblems.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;
import ro.ubb.labproblems.dto.StudentDto;
import ro.ubb.labproblems.model.Student;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDto toDto(Student student);

    List<StudentDto> toDtoList(List<Student> students);

    Student toEntity(StudentDto studentDto);
    Student updateToEntity(StudentDto studentDto, @MappingTarget Student student);

}

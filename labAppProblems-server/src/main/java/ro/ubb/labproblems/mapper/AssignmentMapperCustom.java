package ro.ubb.labproblems.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.labproblems.dto.AssignmentDto;
import ro.ubb.labproblems.model.Assignment;
import ro.ubb.labproblems.model.Problem;
import ro.ubb.labproblems.model.Student;
import ro.ubb.labproblems.mapper.ProblemMapper;
import ro.ubb.labproblems.mapper.StudentMapper;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class AssignmentMapperCustom implements Mapper<AssignmentDto,Assignment>{
    ProblemMapper problemMapper;
    StudentMapper studentMapper;

    @Autowired
    public AssignmentMapperCustom (ProblemMapper problemMapper, StudentMapper studentMapper)
    {
        this.problemMapper=problemMapper;
        this.studentMapper=studentMapper;
    }

    public AssignmentDto toDto(Assignment assignment) {
        AssignmentDto assignmentDto  = new AssignmentDto();
        assignmentDto.setId(assignment.getId());
        assignmentDto.setProblemDto(problemMapper.toDto(assignment.getProblem()));
        assignmentDto.setStudentDto(studentMapper.toDto(assignment.getStudent()));
        assignmentDto.setGrade(assignment.getGrade());
        return assignmentDto;
    }

    public List<AssignmentDto> toDtoList(List<Assignment> assignments) {
        return assignments.stream().map(assignment -> toDto(assignment)).collect(Collectors.toList());
    }

    public Assignment toEntity(AssignmentDto assignmentDto) {
        Assignment assignment=new Assignment();
        assignment.setId(assignmentDto.getId());
        assignment.setGrade(assignmentDto.getGrade());
        assignment.setProblem(problemMapper.toEntity(assignmentDto.getProblemDto()));
        assignment.setStudent(studentMapper.toEntity(assignmentDto.getStudentDto()));
        return assignment;
    }
}

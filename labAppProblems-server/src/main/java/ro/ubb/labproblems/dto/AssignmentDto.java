package ro.ubb.labproblems.dto;


import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class AssignmentDto {


    private Integer id;
    private StudentDto studentDto;
    private ProblemDto problemDto;
    private Double grade;


}

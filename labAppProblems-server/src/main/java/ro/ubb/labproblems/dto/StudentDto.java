package ro.ubb.labproblems.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class StudentDto {

    private Integer id;
    private Integer registrationNumber;
    private String name;
    private Integer groupNumber;
    private List<AssignmentDto> assignments;

}

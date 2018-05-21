package ro.ubb.labproblems.dto;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProblemDto {

    private Integer id;
    private String title;
    private String description;
    private List<AssignmentDto> assignments;

}

package ro.ubb.labproblems.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProblemDto {

    private Integer id;
    private String title;
    private String description;

}

package ro.ubb.labproblems.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
public class Assignment implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @JoinColumn(name = "problem_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Problem problem;

    @JoinColumn(name = "student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @Min(value = 1, message = "What did he do to get less than 1?")
    private Double grade = 1.0;
}

package ro.ubb.labproblems.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
public class Assignment implements Serializable {


    @Id
    @GeneratedValue
    private Integer id;

    @JoinColumn(name = "problem_id")
    @ManyToOne
    private Problem problem;

    @JoinColumn(name = "student_id")
    @ManyToOne
    private Student student;
    private Double grade;


}

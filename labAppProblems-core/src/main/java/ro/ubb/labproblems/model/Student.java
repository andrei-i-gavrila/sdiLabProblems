package ro.ubb.labproblems.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
@NamedEntityGraph(
        name = "studentWithAssignments",
        attributeNodes = @NamedAttributeNode(value = "assignments")
)
public class Student {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer registrationNumber;
    private String name;
    private Integer groupNumber;


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Assignment> assignments;
}

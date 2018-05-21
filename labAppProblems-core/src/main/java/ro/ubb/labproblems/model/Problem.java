package ro.ubb.labproblems.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
@NamedEntityGraph(
        name = "problemWithAssignments",
        attributeNodes = @NamedAttributeNode(value = "assignments")
)
public class Problem implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String description;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Assignment> assignments;
}

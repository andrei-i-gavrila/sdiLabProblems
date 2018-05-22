package ro.ubb.labproblems.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @NotBlank
    @Size(min = 5, message = "Title must have at least 5 characters")
    private String title;
    @NotBlank
    @Size(min = 10, message = "You should write a better description")
    private String description;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Assignment> assignments;
}

package ro.ubb.labproblems.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
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
@Valid
public class Student {

    @Id
    @GeneratedValue
    private Integer id;
    @Digits(integer = 4, fraction = 0, message = "Registration number must have 4 digits")
    private Integer registrationNumber;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @Digits(integer = 3, fraction = 0, message = "Group must have 3 digits")
    private Integer groupNumber;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Assignment> assignments;
}

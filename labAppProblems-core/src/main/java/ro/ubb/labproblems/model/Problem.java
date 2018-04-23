package ro.ubb.labproblems.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
public class Problem implements Serializable {

    @Id
    private Integer id;
    private String title;
    private String description;
}

package ro.ubb.labproblems.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
    @GeneratedValue
    private Integer id;
    private String title;
    private String description;
}

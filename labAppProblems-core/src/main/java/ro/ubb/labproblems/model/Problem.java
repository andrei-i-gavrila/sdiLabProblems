package ro.ubb.labproblems.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Implements the problem entity as a BaseEntity
 */
@Entity
public class Problem implements Serializable {

    @Id
    private String title;
    private String description;


    public Problem() {
    }

    public Problem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return title + '\n' + description;
    }
}

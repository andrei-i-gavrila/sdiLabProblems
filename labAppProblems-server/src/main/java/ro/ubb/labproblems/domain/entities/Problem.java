package ro.ubb.labproblems.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Implements the problem entity as a BaseEntity
 */
public class Problem extends BaseEntity<String> {

    /**
     * The problem's title
     */
    private String title;
    /**
     * The problem's whole description
     */
    private String description;


    public Problem() {
    }

    /**
     * Constructor for the problem entity
     *
     * @param title       Unique title for the problem
     * @param description The problem's description
     */
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

    @JsonIgnore
    @Override
    public String getIdentifier() {
        return title;
    }

    @Override
    public String toString() {
        return title + '\n' + description;
    }
}

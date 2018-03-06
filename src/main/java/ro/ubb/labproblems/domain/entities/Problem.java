package ro.ubb.labproblems.domain.entities;

/**
 * Created by Sandy on 3/5/2018.
 */
public class Problem implements BaseEntity<String> {

    private String title;
    private String description;

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
    public String getIdentifier() {
        return title;
    }

    @Override
    public String toString() {
        return title + '\n' + description;
    }
}

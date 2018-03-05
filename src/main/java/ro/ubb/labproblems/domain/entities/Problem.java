package ro.ubb.labproblems.domain.entities;

/**
 * Created by Sandy on 3/5/2018.
 */
public class Problem implements BaseEntity<Integer> {

    private Integer regNr;
    private String title;
    private String description;

    public Problem(Integer regNr,String tit,String desc)
    {
        this.regNr=regNr;
        this.description=desc;
        this.title=tit;
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
    public Integer getIdentifier() {
        return regNr;
    }

    @Override
    public String toString(){return title + '\n' + description;}
}

package ro.ubb.labproblems.repository;

import ro.ubb.labproblems.domain.entities.BaseEntity;

/**
 * A basic {@link BaseEntity BaseEntity} class, used for testing only
 */
public class MockBaseEntity extends BaseEntity<Integer> {

    public String name;
    private Integer id;

    public MockBaseEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Integer getIdentifier() {
        return id;
    }
}

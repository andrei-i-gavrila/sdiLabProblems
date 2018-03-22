package ro.ubb.labproblems.repository;

import ro.ubb.labproblems.domain.entities.BaseEntity;

/**
 * A basic {@link BaseEntity BaseEntity} class, used for testing only
 */
public class MockBaseEntity extends BaseEntity<String> {

    public String name;
    private String id;

    public MockBaseEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getIdentifier() {
        return id;
    }
}

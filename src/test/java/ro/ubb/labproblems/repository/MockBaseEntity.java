package ro.ubb.labproblems.repository;

import ro.ubb.labproblems.domain.BaseEntity;

public class MockBaseEntity implements BaseEntity<Integer> {

    private Integer id;
    public String name;

    public MockBaseEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Integer getIdentifier() {
        return id;
    }
}

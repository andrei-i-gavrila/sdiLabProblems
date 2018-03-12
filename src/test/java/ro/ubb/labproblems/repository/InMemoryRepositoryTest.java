package ro.ubb.labproblems.repository;

import org.junit.Before;
import org.junit.Test;
import ro.ubb.labproblems.domain.validators.ValidatorException;
import ro.ubb.labproblems.utils.IteratorUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class InMemoryRepositoryTest {

    private Repository<Integer, MockBaseEntity> repository;

    @Before
    public void setUp() {
        this.repository = new InMemoryRepository<>(entity -> {
            // TODO think how to replace if
            if (entity.name.length() == 0) {
                throw new ValidatorException();
            }
        });
    }

    @Test(expected = ValidatorException.class)
    public void saveInvalidThrows() throws ValidatorException {
        repository.save(new MockBaseEntity(1, ""));
    }

    @Test
    public void save() throws ValidatorException {
        assertFalse(repository.save(new MockBaseEntity(1, "1")).isPresent());
        assertFalse(repository.save(new MockBaseEntity(2, "1")).isPresent());
        assertEquals(2, IteratorUtils.size(repository.findAll()));
    }

    @Test
    public void saveExisting() throws ValidatorException {
        MockBaseEntity e = new MockBaseEntity(1, "2");
        repository.save(e);
        assertEquals(e, repository.save(e).orElseThrow(AssertionError::new));
    }

    @Test
    public void deleteNothing() {
        assertFalse(repository.delete(1).isPresent());
        assertEquals(0, IteratorUtils.size(repository.findAll()));
    }

    @Test
    public void delete() throws ValidatorException {
        MockBaseEntity e = new MockBaseEntity(1, "2");
        repository.save(e);

        assertEquals(e, repository.delete(e.getIdentifier()).orElseThrow(AssertionError::new));
        assertEquals(0, IteratorUtils.size(repository.findAll()));
    }

    @Test
    public void update() throws ValidatorException {
        MockBaseEntity e = new MockBaseEntity(1, "2");

        assertEquals(e, repository.update(e).orElseThrow(AssertionError::new));
    }

    @Test
    public void updateExisting() throws ValidatorException {
        MockBaseEntity e = new MockBaseEntity(1, "2");
        repository.save(e);

        assertFalse(repository.update(e).isPresent());
    }

    @Test
    public void findOne() throws ValidatorException {
        MockBaseEntity e = new MockBaseEntity(1, "1");
        repository.save(e);

        assertFalse(repository.find(2).isPresent());
        assertEquals(e, repository.find(1).orElseThrow(AssertionError::new));
    }

    @Test
    public void all() throws ValidatorException {
        MockBaseEntity e = new MockBaseEntity(1, "2");
        repository.save(e);
        repository.save(new MockBaseEntity(2, "3"));

        assertEquals(2, IteratorUtils.size(repository.findAll()));
        assertEquals(e, IteratorUtils.stream(repository.findAll()).findFirst().orElseThrow(AssertionError::new));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findNull() {
        repository.find(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveNull() throws ValidatorException {
        repository.save(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteNull() {
        repository.delete(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNull() throws ValidatorException {
        repository.update(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void requiresValidator() {
        new InMemoryRepository<Integer, MockBaseEntity>(null);
    }


}
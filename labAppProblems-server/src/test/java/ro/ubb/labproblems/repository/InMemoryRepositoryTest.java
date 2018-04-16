package ro.ubb.labproblems.repository;

public class InMemoryRepositoryTest {

//    private Repository<String, MockBaseEntity> repository;
//
//    @Before
//    public void setUp() {
//        this.repository = new InMemoryRepository<>(entity -> {
//            if (entity.name.length() == 0) {
//                throw new ValidatorException("invalid entity", entity);
//            }
//        }, new StorageProvider() {
//
//            List<MockBaseEntity> elements = new ArrayList<>();
//
//            @Override
//            public <T extends BaseEntity<String>> List<T> get(Class<T> type) {
//                //noinspection unchecked
//                return (List<T>) elements;
//            }
//        }, MockBaseEntity.class);
//    }
//
//    @Test(expected = ValidatorException.class)
//    public void saveInvalidThrows() throws ValidatorException {
//        repository.save(new MockBaseEntity("1", ""));
//    }
//
//    @Test
//    public void save() throws ValidatorException {
//        assertFalse(repository.save(new MockBaseEntity("1", "1")).isPresent());
//        assertFalse(repository.save(new MockBaseEntity("2", "1")).isPresent());
//        assertEquals(2, IteratorUtils.size(repository.findAll()));
//    }
//
//    @Test
//    public void saveExisting() throws ValidatorException {
//        MockBaseEntity e = new MockBaseEntity("1", "2");
//        repository.save(e);
//        assertEquals(e, repository.save(e).orElseThrow(AssertionError::new));
//    }
//
//    @Test
//    public void deleteNothing() {
//        assertFalse(repository.delete("1").isPresent());
//        assertEquals(0, IteratorUtils.size(repository.findAll()));
//    }
//
//    @Test
//    public void delete() throws ValidatorException {
//        MockBaseEntity e = new MockBaseEntity("1", "2");
//        repository.save(e);
//
//        assertEquals(e, repository.delete(e.getIdentifier()).orElseThrow(AssertionError::new));
//        assertEquals(0, IteratorUtils.size(repository.findAll()));
//    }
//
//    @Test
//    public void update() throws ValidatorException {
//        MockBaseEntity e = new MockBaseEntity("1", "2");
//
//        assertEquals(e, repository.update(e).orElseThrow(AssertionError::new));
//    }
//
//    @Test
//    public void updateExisting() throws ValidatorException {
//        MockBaseEntity e = new MockBaseEntity("1", "2");
//        repository.save(e);
//
//        assertFalse(repository.update(e).isPresent());
//    }
//
//    @Test
//    public void findOne() throws ValidatorException {
//        MockBaseEntity e = new MockBaseEntity("1", "1");
//        repository.save(e);
//
//        assertFalse(repository.find("2").isPresent());
//        assertEquals(e, repository.find("1").orElseThrow(AssertionError::new));
//    }
//
//    @Test
//    public void all() throws ValidatorException {
//        MockBaseEntity e = new MockBaseEntity("1", "2");
//        repository.save(e);
//        repository.save(new MockBaseEntity("2", "3"));
//
//        assertEquals(2, IteratorUtils.size(repository.findAll()));
//        assertEquals(e, IteratorUtils.stream(repository.findAll()).findFirst().orElseThrow(AssertionError::new));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void findNull() {
//        repository.find(null);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void saveNull() throws ValidatorException {
//        repository.save(null);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void deleteNull() {
//        repository.delete(null);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void requiresValidator() {
//        new InMemoryRepository<MockBaseEntity>(null, null, null);
//    }


}
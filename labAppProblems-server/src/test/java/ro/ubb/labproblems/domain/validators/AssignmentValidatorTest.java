package ro.ubb.labproblems.domain.validators;

public class AssignmentValidatorTest {

//    private Repository<String, Student> studentRepository;
//    private Repository<String, Problem> problemRepository;
//    private AssignmentValidator assignmentValidator;
//
//    @Before
//    public void setUp() throws Exception {
//        StorageProvider storageProvider = new StorageProvider();
//        studentRepository = new InMemoryRepository<>(new StudentValidator(), storageProvider, Student.class);
//        problemRepository = new InMemoryRepository<>(new ProblemValidator(), storageProvider, Problem.class);
//
//        assignmentValidator = new AssignmentValidator(studentRepository, problemRepository);
//    }
//
//    @Test
//    public void validatesCorrectEntity() throws ValidatorException {
//        studentRepository.save(new Student("1234", "Andrei", 926));
//        problemRepository.save(new Problem("knapsack", "dp problem"));
//
//        try {
//            assignmentValidator.validate(new AssignmentMapper("knapsack", "1234"));
//        } catch (ValidatorException e) {
//            fail("It should not fail");
//        }
//    }
//
//    @Test
//    public void failsNoStudent() throws ValidatorException {
//        problemRepository.save(new Problem("asd", "asddd"));
//
//        try {
//            assignmentValidator.validate(new AssignmentMapper("asd", "1234"));
//            fail();
//        } catch (ValidatorException e) {
//            assertEquals("Registration number does not exist", e.getMessage());
//        }
//    }
//
//    @Test
//    public void failsNoProblem() throws ValidatorException {
//        studentRepository.save(new Student("1234", "asd", 927));
//
//        try {
//            assignmentValidator.validate(new AssignmentMapper("ddsd", "1234"));
//            fail();
//        } catch (ValidatorException e) {
//            assertEquals("Problem title does not exist", e.getMessage());
//        }
//    }
//
//    @Test
//    public void failsNegativeGrade() throws ValidatorException {
//        studentRepository.save(new Student("1234", "Andrei", 926));
//        problemRepository.save(new Problem("knapsack", "dp problem"));
//
//        try {
//            AssignmentMapper assignment = new AssignmentMapper("knapsack", "1234");
//            assignment.setGrade(-1.0);
//            assignmentValidator.validate(assignment);
//            fail();
//        } catch (ValidatorException e) {
//            assertEquals("Grade cannot be negative", e.getMessage());
//        }
//    }

}
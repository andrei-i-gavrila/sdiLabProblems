package ro.ubb.labproblems.service;

public class ProblemServiceTest {
//    private static final String TITLE1 = "title1";
//    private static final String TITLE2 = "title2";
//    private static final String DESCRIPTION1 = "DESCRIPTION1";
//    private static final String DESCRIPTION2 = "DESCRIPTION1";
//    private Problem problem = new Problem(TITLE1, DESCRIPTION1);
//    private StorageProvider storageProvider = new StorageProvider();
//    private Repository<String, Student> studentRepository = new InMemoryRepository<>(new StudentValidator(), storageProvider, Student.class);
//    private Repository<String, Problem> problemRepository = new InMemoryRepository<>(new ProblemValidator(), storageProvider, Problem.class);
//
//    private Repository<String, AssignmentMapper> assignmentRepository = new InMemoryRepository<>(new AssignmentValidator(studentRepository, problemRepository), storageProvider, AssignmentMapper.class);
//    private ProblemService problemService = new ProblemServiceServer(problemRepository, assignmentRepository);
//
//
//    @Test
//    public void testAdd() {
//        assertEquals("Problem added successfully", problemService.add(TITLE1, DESCRIPTION1));
//        assertTrue(problemRepository.find(TITLE1).isPresent());
//    }
//
//    @Test
//    public void testRemove() {
//        problemService.add(TITLE1, DESCRIPTION1);
//        assertEquals("No problem with such title was found", problemService.remove(TITLE2));
//        assertEquals("Problem removed successfully", problemService.remove(TITLE1));
//        assertFalse(problemRepository.find(TITLE1).isPresent());
//    }
//
//    @Test
//    public void testUpdate() {
//        problemService.add(TITLE1, DESCRIPTION1);
//        assertEquals("No problem was found with the given title", problemService.update(TITLE2, DESCRIPTION1));
//        assertEquals("Problem updated successfully", problemService.update(TITLE1, DESCRIPTION2));
//
//        Optional<Problem> problemFromRepository = problemRepository.find(TITLE1);
//        assertTrue(problemFromRepository.isPresent());
//        assertNotEquals(problemFromRepository.get(), problem);
//        assertEquals(problemFromRepository.get().getDescription(), DESCRIPTION2);
//    }
//
//    @Test
//    public void testPrintAll() {
//        problemService.add(TITLE1, DESCRIPTION1);
//        assertEquals(problem.toString(), problemService.showAll());
//
//        Problem problem2 = new Problem(TITLE2, DESCRIPTION2);
//        problemService.add(TITLE2, DESCRIPTION2);
//        assertEquals(problem + "\n" + problem2, problemService.showAll());
//    }
//
//    @Test
//    public void testValidationHappens() {
//        assertEquals("The title can't be empty", problemService.add("", DESCRIPTION1));
//        assertEquals("The title can't be empty", problemService.update("", DESCRIPTION1));
//    }
}

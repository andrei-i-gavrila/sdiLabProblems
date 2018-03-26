package ro.ubb.labproblems.service;


public interface AssignmentService {
    /**
     * Assigns a given problem to a given student
     *
     * @param problemTitle              The title of the problem
     * @param studentRegistrationNumber The registration number of the student
     * @return A message telling the assignment was successful, or the reason why it wasn't
     */
    String assign(String problemTitle, String studentRegistrationNumber);

    /**
     * Assigns a grade to a given assignment
     *
     * @param problemTitle              The title of the problem
     * @param studentRegistrationNumber The registration number of the student
     * @param grade                     The given grade
     * @return A message telling the assignment was successful, or the reason why it wasn't
     */
    String grade(String problemTitle, String studentRegistrationNumber, Double grade);

    /**
     * Unassigns a problem from a student
     *
     * @param problemTitle              The title of the problem
     * @param studentRegistrationNumber The registration number of the student
     * @return A message telling the unassignment was successful, or the reason why it wasn't
     */
    String unassign(String problemTitle, String studentRegistrationNumber);

    /**
     * Returns those assignments that are assigned to a given student
     *
     * @param registrationNumber The registration number of the student
     * @return A list of filtered assignments
     */
    String filterByStudent(String registrationNumber);

    /**
     * Returns those assignments that contain a given problem, and the grade given for it is greater or equal to the given grade
     *
     * @param problemTitle The title of the problem
     * @return A list of filtered assignments
     */
    String filterByGrade(String problemTitle, Double grade);

    /**
     * @return String representation of all assignments from the repository
     */
    String showAll();
}

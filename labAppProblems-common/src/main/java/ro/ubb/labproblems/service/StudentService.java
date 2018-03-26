package ro.ubb.labproblems.service;


public interface StudentService {
    /**
     * Constructs and adds a student to the Repository
     *
     * @param name               The full name of the student
     * @param registrationNumber The registration number of the student
     * @param groupNumber        The group number of the student
     * @return The string containing the success message, or the reason it failed
     */
    String add(String name, String registrationNumber, Integer groupNumber);

    /**
     * Removes the student with the given registration number from the Repository
     *
     * @param registrationNumber The registration number of the student to be removed
     * @return The string containing the success message, or the reason it failed
     */
    String remove(String registrationNumber);

    /**
     * Updates the Student with the given registration number to have a new name, and/or a new group number
     *
     * @param name               The current/new name student
     * @param registrationNumber The identifier(registration number)
     * @param groupNumber        The current/new group number
     * @return The string containing the success message, or the reason it failed
     */
    String update(String name, String registrationNumber, Integer groupNumber);

    /**
     * @return String representation of all students from the Repository
     */
    String showAll();

    /**
     * All students of a given group as a list
     *
     * @param groupNumber The group's number
     * @return String with all students from group given
     */
    String filterByGroup(Integer groupNumber);

    /**
     * Finds the best student based on the average of his/her grades
     *
     * @return The identification number of the student
     */
    String bestStudent();

    String getStudentAverageGrade(String studentRegistrationNumber);
}

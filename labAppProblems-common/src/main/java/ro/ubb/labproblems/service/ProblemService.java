package ro.ubb.labproblems.service;


public interface ProblemService {
    /**
     * Constructs and adds a Problem to the Repository
     *
     * @param title       The title of the problem
     * @param description The description of the problem
     * @return The string containing the success message, or the reason it failed
     */
    String add(String title, String description);

    /**
     * Removes the Problem with the given title from the Repository
     *
     * @param title The title of the Problem to be removed
     * @return The string containing the success message, or the reason it failed
     */
    String remove(String title);

    /**
     * Updates the Problem with the given title to have a new description
     *
     * @param title       identifier for the Problem
     * @param description new description
     * @return The string containing the success message, or the reason it failed
     */
    String update(String title, String description);

    /**
     * @return String representation of all Problems from the Repository
     */
    String showAll();

    /**
     * Finds the problem that was assigned most times
     *
     * @return The title of the most assigned problem
     */
    String mostAssignedProblem();
}

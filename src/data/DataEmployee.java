package data;

import java.util.ArrayList;

/**
 * Class that represents the data of an employee related to machine problems.
 * This class implements the Data interface and contains information about the
 * issues encountered in a specific machine.
 */
public class DataEmployee implements Data {

    // List of problems associated with the machine
    private ArrayList<String> problems;

    // Name of the machine
    public String machineName;

    /**
     * Constructor that initializes the employee data.
     *
     * @param problems List of problems related to the machine.
     * @param machineName Name of the machine associated with the employee.
     */
    public DataEmployee(ArrayList<String> problems, String machineName) {
        this.problems = problems;
        this.machineName = machineName;
    }

    /**
     * Default constructor that creates an instance of DataEmployee without
     * initializing data. This may be useful in situations where data will
     * be defined later.
     */
    public DataEmployee() {}

    /**
     * Method to get the name of the machine.
     *
     * @return the name of the machine associated with the employee.
     */
    public String getMachineName() {
        return machineName;
    }

    /**
     * Method to get the list of problems associated with the machine.
     *
     * @return the list of problems.
     */
    public ArrayList<String> getProblems() {
        return problems;
    }

}

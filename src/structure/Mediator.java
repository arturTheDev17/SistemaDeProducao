package structure;

import data.Data;
import data.DataEmployee;
import data.DataMachine;
import panel.EmployeePanel;
import panel.InformationPanel;

import java.util.ArrayList;

/**
 * The Mediator class implements the Observer and Observable interfaces,
 * serving as a central hub for communication between various panels in the application.
 * It follows the Mediator design pattern, facilitating loose coupling between objects.
 */
public class Mediator implements Observer, Observable {

    // Lists to keep track of observers for different panels
    public ArrayList<Observer> panelObservers = new ArrayList<>();
    public ArrayList<Observer> panelEmployees  = new ArrayList<>();

    // Singleton instance of Mediator
    private static Mediator mediator;

    // Private constructor to prevent instantiation
    private Mediator () {}

    /**
     * Returns the singleton instance of the Mediator.
     * Ensures only one instance of Mediator exists.
     *
     * @return Mediator instance
     */
    public static Mediator getInstance() {
        if (mediator == null) {
            mediator = new Mediator();
        }
        return mediator;
    }

    /**
     * Updates observers when new data is received.
     *
     * @param dataMachine The new data to be published.
     */
    @Override
    public void update(Data dataMachine) {
        this.publish(dataMachine);
    }

    /**
     * Subscribes an observer to the Mediator.
     * Distinguishes between InformationPanel and EmployeePanel to manage them separately.
     *
     * @param observer The observer to subscribe.
     */
    @Override
    public void subscribe(Observer observer) {
        if (observer instanceof InformationPanel) {
            this.panelObservers.add(observer);
        } else if (observer instanceof EmployeePanel) {
            this.panelEmployees.add(observer);
        }
    }

    /**
     * Unsubscribes an observer from the Mediator.
     *
     * @param observer The observer to unsubscribe.
     */
    @Override
    public void unsubscribe(Observer observer) {
        this.panelObservers.remove(observer);
    }

    /**
     * Publishes new data to all subscribed observers.
     *
     * @param dataMachine The data to publish to observers.
     */
    @Override
    public void publish(Data dataMachine) {
        // Notify all observers in the panelObservers list
        for (Observer observer : panelObservers) {
            observer.update(dataMachine);
        }
        // Notify all observers in the panelEmployees list, after formatting the data
        for (Observer observer : panelEmployees) {
            DataEmployee dataEmployee = formatter((DataMachine) dataMachine);
            observer.update(dataEmployee);
        }
    }

    /**
     * Formats the data from DataMachine into DataEmployee.
     * This includes checking thresholds and identifying problems.
     *
     * @param dataMachine The DataMachine object to format.
     * @return A DataEmployee object with formatted problem information.
     */
    private static DataEmployee formatter(DataMachine dataMachine) {
        ArrayList<String> problems = new ArrayList<>();

        // Check each attribute for issues
        for (String name : dataMachine.getATRIBUTTES().keySet()) {
            // Handle Boolean attributes
            if (dataMachine.getATRIBUTTES().get(name) instanceof Boolean bool && bool) {
                problems.add(name + " is " + bool);
            }
            // Handle numeric attributes represented as an array
            else if (dataMachine.getATRIBUTTES().get(name) instanceof Number[] num) {
                // Check for minimum threshold violations
                if ((double) num[0] > (double) num[2]) {
                    problems.add(name + " surpassed the minimum threshold of " + num[0] + ".");
                }
                // Check for maximum threshold violations
                if ((double) num[1] < (double) num[2]) {
                    problems.add(name + " exceeded the maximum threshold of " + num[1] + ".");
                }
            }
        }
        // Return a new DataEmployee with identified problems
        return new DataEmployee(problems, dataMachine.getMachineName());
    }
}

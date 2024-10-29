package factory;

import machine.Machine;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Class responsible for creating instances of the Machine class.
 * This class implements the Singleton design pattern to ensure that
 * there is only one instance of MachineMaker.
 * Additionally, it uses the Factory pattern for creating objects of type Machine.
 *
 * The Factory pattern allows for decoupling the object creation logic,
 * facilitating the addition of new implementations without the need to modify
 * existing code.
 */
public class MachineMaker implements MachineMakerInterface {

    // Singleton instance of the MachineMaker class
    private static MachineMaker machineMaker;

    // Private constructor to prevent external instantiation
    private MachineMaker() {}

    /**
     * Method to obtain the singleton instance of MachineMaker.
     * If the instance does not exist, it will be created.
     * @return the singleton instance of MachineMaker
     */
    public static MachineMaker getInstance() {
        if (machineMaker == null) {
            machineMaker = new MachineMaker();
        }
        return machineMaker;
    }

    /**
     * Method that creates a new instance of the Machine class.
     * This method represents the Factory pattern, allowing the creation
     * of Machine objects without the client code needing to know
     * the details of its implementation.
     *
     * @param machineName Name of the machine to be created.
     * @param map Map containing attributes of the machine.
     * @return a new Machine instance
     */
    @Override
    public Machine newMachine(String machineName, HashMap<String, Serializable> map) {
        return new Machine(machineName, map);
    }
}

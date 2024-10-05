package factory;

import machine.Machine;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Interface for creating machines.
 * Defines the contract for classes that implement the Factory pattern,
 * allowing the creation of different types of machines.
 */
public interface MachineMakerInterface {
    /**
     * Method to create a new instance of Machine.
     *
     * @param machineName Name of the machine to be created.
     * @param map Map containing attributes of the machine.
     * @return a new Machine instance
     */
    Machine newMachine(String machineName, HashMap<String, Serializable> map);
}

package data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Class that represents the data of a machine.
 * This class implements the Data interface and stores information about attributes
 * related to a specific machine.
 */
public class DataMachine implements Data {

    // Name of the machine
    private String machineName;

    /**
     * Map that stores attributes of the machine.
     * Boolean values are always true in case of error.
     */
    private final HashMap<String, Serializable> ATRIBUTTES;

    /**
     * Default constructor that initializes the HashMap of attributes.
     * This allows for the creation of a DataMachine instance without initial data.
     */
    public DataMachine() {
        ATRIBUTTES = new HashMap<>();
    }

    /**
     * Constructor that initializes the machine data with a name and a map of attributes.
     *
     * @param machineName Name of the machine associated with the data.
     * @param map Map containing the attributes of the machine, where each attribute is a key-value pair.
     */
    public DataMachine(String machineName, HashMap<String, Serializable> map) {
        this.ATRIBUTTES = map;
        this.machineName = machineName;
    }

    /**
     * Method to add an attribute to the machine.
     *
     * @param name Name of the attribute.
     * @param value Array of serializable values associated with the attribute.
     */
    public void addAttribute(String name, Serializable[] value) {
        ATRIBUTTES.put(name, value);
    }

    /**
     * Method to add an attribute to the machine.
     *
     * @param name Name of the attribute.
     * @param value Serializable value associated with the attribute.
     */
    public void addAttribute(String name, Serializable value) {
        ATRIBUTTES.put(name, value);
    }

    /**
     * Method to get the name of the machine.
     *
     * @return the name of the machine associated with the data.
     */
    public String getMachineName() {
        return machineName;
    }

    /**
     * Method to get the map of machine attributes.
     *
     * @return the map of attributes.
     */
    public HashMap<String, Serializable> getATRIBUTTES() {
        return ATRIBUTTES;
    }
}

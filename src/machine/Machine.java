package machine;

import data.Data;
import data.DataMachine;
import structure.Mediator;
import structure.Observable;
import structure.Observer;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Represents a machine that can hold data and notify observers about changes.
 * This class implements the Observable interface, allowing it to interact
 * with a mediator and manage its state through data attributes.
 */
public class Machine implements Observable {

    // Mediator for handling communication with observers
    private Mediator mediator;

    // Data associated with the machine
    private final DataMachine ownData;

    /**
     * Constructor for creating a Machine instance with a name and attributes.
     *
     * @param machineName Name of the machine.
     * @param map Map containing attributes of the machine.
     */
    public Machine(String machineName, HashMap<String, Serializable> map) {
        this.ownData = new DataMachine(machineName, map);
    }

    /**
     * Method to get the current mediator.
     *
     * @return the mediator managing the machine's observers.
     */
    public Mediator getMediator() {
        return mediator;
    }

    /**
     * Method to get the data associated with this machine.
     *
     * @return the DataMachine instance containing machine data.
     */
    public DataMachine getOwnData() {
        return ownData;
    }

    /**
     * Method to add an attribute to the machine's data.
     *
     * @param name Name of the attribute.
     * @param value Array of Double values associated with the attribute.
     */
    public void addAttribute(String name, Double[] value) {
        ownData.addAttribute(name, value);
    }

    /**
     * Method to add an attribute to the machine's data.
     *
     * @param name Name of the attribute.
     * @param value Boolean value associated with the attribute.
     */
    public void addAttribute(String name, Boolean value) {
        ownData.addAttribute(name, value);
    }

    /**
     * Method to subscribe an observer to this machine's data updates.
     *
     * @param observer The observer that will be notified of changes.
     */
    @Override
    public void subscribe(Observer observer) {
        this.mediator = (Mediator) observer;
    }

    /**
     * Method to unsubscribe an observer from this machine's data updates.
     *
     * @param observer The observer that will no longer receive updates.
     */
    @Override
    public void unsubscribe(Observer observer) {
        this.mediator = null;
    }

    /**
     * Method to change the machine's data and notify observers.
     *
     * @param dataMachine The new data to be published to observers.
     */
    public void changeData(DataMachine dataMachine) {
        publish(dataMachine);
    }

    /**
     * Method to publish data updates to the mediator.
     *
     * @param data The data to be sent to observers.
     */
    @Override
    public void publish(Data data) {
        mediator.update(this.ownData);
    }
}

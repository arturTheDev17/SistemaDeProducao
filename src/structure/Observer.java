package structure;

import data.Data;

/**
 * The Observer interface defines the contract for objects that wish to receive
 * updates from an Observable. This is part of the Observer design pattern,
 * which establishes a one-to-many dependency between objects.
 */
public interface Observer {

    /**
     * Updates the observer with new data from the observable.
     * This method is called by the observable to notify the observer
     * of changes or events that it should respond to.
     *
     * @param data The data sent by the observable, containing relevant information
     *             about the event or state change.
     */
    void update(Data data);
}

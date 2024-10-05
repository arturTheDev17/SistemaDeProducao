package structure;

import data.Data;

/**
 * The Observable interface defines the contract for objects that can be observed
 * by other components in the application. It is part of the Observer design pattern,
 * which enables a one-to-many dependency between objects.
 */
public interface Observable {

    /**
     * Subscribes an observer to this observable.
     * When changes occur, this observer will be notified.
     *
     * @param observer The observer to subscribe.
     */
    void subscribe(Observer observer);

    /**
     * Publishes data to all subscribed observers.
     * Observers will receive updates with the provided data.
     *
     * @param data The data to be sent to observers.
     */
    void publish(Data data);

    /**
     * Unsubscribes an observer from this observable.
     * The observer will no longer receive updates from this observable.
     *
     * @param observer The observer to unsubscribe.
     */
    void unsubscribe(Observer observer);
}

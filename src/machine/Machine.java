package machine;

import structure.Mediator;
import structure.Observable;
import structure.Observer;

public abstract class Machine implements Observable {

    private Mediator mediator;

    @Override
    public void subscribe(Observer observer) {
        this.mediator = (Mediator)observer;
    }

    @Override
    public void unsubscribe(Observer observer) {
        this.mediator = null;
    }
}

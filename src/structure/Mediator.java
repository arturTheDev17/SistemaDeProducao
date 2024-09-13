package structure;

import java.util.ArrayList;
public class Mediator implements Observer, Observable {

    private Mediator mediator;
    public ArrayList<Observer> panelObservers;



    public Mediator getInstance() {
        if (this.mediator == null) {
            this.mediator = new Mediator();
        }

        return this.mediator;
    }

    @Override
    public void update(String data) {

    }

    @Override
    public void subscribe(Observer observer) {

    }

    @Override
    public void unsubscribe(Observer observer) {

    }
}

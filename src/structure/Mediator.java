package structure;

import data.Data;
import data.DataMachine;
import java.util.ArrayList;

public class Mediator implements Observer, Observable {

    public ArrayList<Observer> panelObservers = new ArrayList<>();
    public ArrayList<Observer> panelEmployees  = new ArrayList<>();

    private Mediator mediator;
    public Mediator getInstance() {
        if (this.mediator == null) {
            this.mediator = new Mediator();
        }

        return this.mediator;
    }

    @Override
    public void update(Data dataMachine) {
        this.publish(dataMachine);
    }

    //Methods of ObserverString

//    @Override
//    public void subscribe(ObserverString observer) {
//        this.panelEmployees.add(observer);
//    }

//    @Override
//    public void publish(String data) {
//        //TODO publish to employees
//    }

//    @Override
//    public void unsubscribe(ObserverString observer) {
//        this.panelEmployees.remove(observer);
//    }

    //Methods of ObserverData

    @Override
    public void subscribe(Observer observer) {
        this.panelObservers.add(observer);
    }

    @Override
    public void publish(Data dataMachine) {
        for( Observer observer : panelObservers ) {
            observer.update(dataMachine);
        }
    }

    @Override
    public void unsubscribe(Observer observer) {
        this.panelObservers.remove(observer);
    }

}

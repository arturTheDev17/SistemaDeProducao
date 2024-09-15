package structure;

import data.Data;
import java.util.ArrayList;

public class Mediator implements ObserverData, ObservableData, ObservableString {

    public ArrayList<ObserverData> panelObservers = new ArrayList<>();
    public ArrayList<ObserverString> panelEmployees  = new ArrayList<>();

    private Mediator mediator;
    public Mediator getInstance() {
        if (this.mediator == null) {
            this.mediator = new Mediator();
        }

        return this.mediator;
    }

    @Override
    public void update(Data data) {
        this.publish(data);
    }

    //Methods of ObserverString

    @Override
    public void subscribe(ObserverString observer) {
        this.panelEmployees.add(observer);
    }

    @Override
    public void publish(String data) {
        //TODO publish to employees
    }

    @Override
    public void unsubscribe(ObserverString observer) {
        this.panelEmployees.remove(observer);
    }

    //Methods of ObserverData

    @Override
    public void subscribe(ObserverData observer) {
        this.panelObservers.add(observer);
    }

    @Override
    public void publish( Data data ){
        for( ObserverData observer : panelObservers ) {
            observer.update( data );
        }
    }

    @Override
    public void unsubscribe(ObserverData observer) {
        this.panelObservers.remove(observer);
    }
}

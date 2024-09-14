package structure;

import data.Data;
import java.util.ArrayList;

public class Mediator implements ObserverData, ObservableString {

    public ArrayList<ObserverString> panelObservers = new ArrayList<>();
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
        this.publish(data.toString());
    }

    @Override
    public void subscribe(ObserverString observer) {
        this.panelObservers.add(observer);
    }

    @Override
    public void unsubscribe(ObserverString observer) {
        this.panelObservers.remove(observer);
    }

    @Override
    public void publish( String data ){
        for( ObserverString observer : panelObservers ) {
            observer.update( data );
        }
    }
}

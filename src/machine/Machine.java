package machine;

import data.Data;
import structure.*;

public abstract class Machine implements ObservableData {

    private Mediator mediator;

    public Mediator getMediator() {
        return mediator;
    }

    @Override
    public void subscribe(ObserverData observer) {
        this.mediator = (Mediator)observer;
    }

    @Override
    public void unsubscribe(ObserverData observer) {
        this.mediator = null;
    }

    //sensor atualizou
    public abstract void changeData(Data data);
}

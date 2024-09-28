package machine;

import data.Data;
import data.DataMachine;
import structure.Mediator;
import structure.Observable;
import structure.Observer;

import java.util.HashMap;

public class Machine implements Observable {

    private Mediator mediator;

    private final DataMachine ownData = new DataMachine();

    /* if (null) -> 1 dos dois ou if (checkbox) -> boolean if(2 null) -> erro
     * else ( verifica minimo e maximo )
     * ISSO É PARA ADICIONAR MODELO
     * */

    public Mediator getMediator() {
        return mediator;
    }

    public void addAttribute( String name , Object value ) {
        ownData.addAttribute( name, value );
    }

    @Override
    public void subscribe(Observer observer) {
        this.mediator = (Mediator)observer;
    }

    @Override
    public void unsubscribe(Observer observer) {
        this.mediator = null;
    }

    public void changeData(DataMachine dataMachine){
        publish( dataMachine );
    }

    @Override
    public void publish(Data data) {

    }
}

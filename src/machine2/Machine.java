package machine2;

import data.DataMachine;
import structure.Mediator;
import structure.Observable;
import structure.Observer;

import java.util.HashMap;

public abstract class Machine implements Observable {

    private Mediator mediator;

    private final HashMap<String , Object> ATRIBUTTES = new HashMap<>();

    /*
    * if (null) -> 1 dos dois ou if (checkbox) -> boolean if(2 null) -> erro
    * else ( verifica minimo e maximo )
    * ISSO É PARA ADICIONAR MODELO
    * */

    public Machine ( ) {

    }

    public Mediator getMediator() {
        return mediator;
    }

    public void addAttribute( String name , Object value ) {
        ATRIBUTTES.put( name , value );
    }

    @Override
    public void subscribe(Observer observer) {
        this.mediator = (Mediator)observer;
    }

    @Override
    public void unsubscribe(Observer observer) {
        this.mediator = null;
    }

    public abstract void changeData(DataMachine dataMachine);

}

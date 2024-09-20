package machine;

import data.DataMachine;
import structure.*;

public abstract class Machine implements Observable {

    private Mediator mediator;
    private String machineName;
    private double maxTemperature;
    private double minTemperature;

    public Machine ( String name , double minTemperature , double maxTemperature ) {
        this.machineName = name;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
    }

    public Machine () {

    }
    public Mediator getMediator() {
        return mediator;
    }

    @Override
    public void subscribe(Observer observer) {
        this.mediator = (Mediator)observer;
    }

    @Override
    public void unsubscribe(Observer observer) {
        this.mediator = null;
    }

    //sensor atualizou
    public abstract void changeData(DataMachine dataMachine);

    public String getMachineName() {
        return machineName;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }
}

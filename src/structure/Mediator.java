package structure;

import data.Data;
import data.DataEmployee;
import data.DataMachine;
import data.DataWelder;
import panel.EmployeePanel;
import panel.InformationPanel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Mediator implements Observer, Observable {

    public ArrayList<Observer> panelObservers = new ArrayList<>();
    public ArrayList<Observer> panelEmployees  = new ArrayList<>();

    private static Mediator mediator;
    private Mediator () {}

    public static Mediator getInstance() {
        if (mediator == null) {
            mediator = new Mediator();
        }

        return mediator;
    }

    @Override
    public void update(Data dataMachine) {
        this.publish(dataMachine);
    }
    @Override
    public void subscribe(Observer observer) {
        if (observer instanceof InformationPanel) {
            this.panelObservers.add(observer);
        } else if (observer instanceof EmployeePanel){
            this.panelEmployees.add(observer);
        }
    }

    @Override
    public void publish(Data dataMachine) {
        for( Observer observer : panelObservers ) {
            observer.update(dataMachine);
        }
        for( Observer observer : panelEmployees) {
            DataEmployee dataEmployee = formatter( dataMachine );
            observer.update(dataEmployee);
        }
    }

    private static DataEmployee formatter( Data dataMachine ) {
        ArrayList<String> problems = new ArrayList<>();
        if ( dataMachine instanceof DataWelder dataWelder ) {

            if ( dataWelder.getParameters()[0] > dataWelder.getTemperature() ){
                problems.add( "Frozen" );
            }
            if ( dataWelder.getParameters()[1] < dataWelder.getTemperature() ){
                problems.add( "Overheated" );
            }
            if ( dataWelder.getParameters()[2] < dataWelder.getCurrent() ){
                problems.add( "Excessive Current" );
            }
            if ( dataWelder.getParameters()[3] < dataWelder.getActiveTime() ) {
                problems.add("Excessive Active Time");
            }
        }
        return new DataEmployee( problems , "Operator" ,((DataMachine)dataMachine).getMachineName() );

    }

    @Override
    public void unsubscribe(Observer observer) {
        this.panelObservers.remove(observer);
    }

}

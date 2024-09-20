package machine;

import data.Data;
import data.DataMachine;
import data.DataWelder;

import java.text.DecimalFormat;

public class Welder extends Machine {
    private double nominalCurrent;
    public Welder() {
        super( "Welder", 0, 100);
        this.nominalCurrent = 50;
        this.data = new DataWelder( "Welder" , 25, 0 , 0 );

    }

    public Welder( String name , double minTemperature , double maxTemperature , double nominalCurrent) {
        super( name, minTemperature, maxTemperature);
        this.nominalCurrent = nominalCurrent;
        this.data = new DataWelder( name , ( minTemperature + maxTemperature ) / 2, 0 , 0 );
    }

    private DataWelder data;

    @Override
    public void publish( Data dataMachine) {
        getMediator().update(dataMachine);
    }

    @Override
    public void changeData(DataMachine dataMachine) {
        DecimalFormat df2 = new DecimalFormat("#,00");
        dataMachine.setTemperature(Double.parseDouble(df2.format(dataMachine.getTemperature())));
        ((DataWelder) dataMachine).setActiveTime(Double.parseDouble(df2.format(((DataWelder) dataMachine).getActiveTime())));
        ((DataWelder) dataMachine).setCurrent(Double.parseDouble(df2.format(((DataWelder) dataMachine).getCurrent())));

        if (this.data != null) {
            String machineName = this.data.getMachineName();
            this.data = (DataWelder) dataMachine;
            this.data.setMachineName(machineName);
        } else {
            this.data = (DataWelder) dataMachine;
        }
        publish(this.data);
    }

}
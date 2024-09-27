package machine;

import data.Data;
import data.DataMachine;
import data.DataWelder;

import java.text.DecimalFormat;

public class Welder extends Machine {
//    private double nominalCurrent;
//    private double activeTimeThreshold;
    private static int welderCounter = 1;
    public Welder() {
        super( "Welder" + welderCounter, 0, 100);
        this.data = new DataWelder( new double[]{ getMinTemperature() , getMaxTemperature() , 50 , 60 } , "Welder" + welderCounter , 25, 0 , 0 );
        welderCounter++;
//        this.nominalCurrent = 50;
    }

    public Welder( String name , double minTemperature , double maxTemperature , double nominalCurrent , double activeTimeThreshold ) {
        super( name, minTemperature, maxTemperature);
//      this.nominalCurrent = nominalCurrent;
//      this.activeTimeThreshold = activeTimeThreshold;
        this.data = new DataWelder( new double[]{ getMinTemperature() , getMaxTemperature() , nominalCurrent , activeTimeThreshold } , name , ( minTemperature + maxTemperature ) / 2, 0 , 0 );
    }

    private DataWelder data;

    public DataWelder getData() {
        return data;
    }

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

        this.data = (DataWelder) dataMachine;
        publish(this.data);
    }

}
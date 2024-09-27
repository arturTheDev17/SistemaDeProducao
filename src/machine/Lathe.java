package machine;

import data.Data;
import data.DataLathe;
import data.DataMachine;
import data.DataWelder;

import java.text.DecimalFormat;

public class Lathe extends Machine {
    private static int latheCounter = 1;
    private DataLathe data;

    public DataLathe getData() {
        return data;
    }

    public Lathe() {
        super( "Lathe" + latheCounter, 0, 100);
        this.data = new DataLathe( new double[]{ getMinTemperature() , getMaxTemperature() , 50 } , "Lathe" + latheCounter , 25, 0 , false );
        latheCounter++;
    }

    public Lathe( String name , double minTemperature , double maxTemperature , int rotationSpeed , boolean collision ) {
        super( name, minTemperature, maxTemperature);
//      this.nominalCurrent = nominalCurrent;
//      this.activeTimeThreshold = activeTimeThreshold;
        this.data = new DataLathe( new double[]{ getMinTemperature() , getMaxTemperature() , rotationSpeed } , name , ( minTemperature + maxTemperature ) / 2, 0 , collision );
    }

    @Override
    public void publish( Data dataMachine) {
        getMediator().update(dataMachine);
    }

    @Override
    public void changeData(DataMachine dataMachine) {
        //Round the data numbers
        dataMachine = (DataLathe) dataMachine;

        DecimalFormat df2 = new DecimalFormat("#,00");
        dataMachine.setTemperature(Double.parseDouble(df2.format(dataMachine.getTemperature())));

        if (this.data != null) {
            String machineName = this.data.getMachineName();
            this.data = (DataLathe) dataMachine;
            this.data.setMachineName(machineName);
        } else {
            this.data = (DataLathe) dataMachine;
        }

        publish(this.data);
    }

}

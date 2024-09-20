package machine;

import data.Data;
import data.DataLathe;
import data.DataMachine;
import java.text.DecimalFormat;

public class Lathe extends Machine {


    public Lathe() {
    }

    public Lathe(DataLathe data) {
        this.data = data;
    }

    private DataLathe data;

    @Override
    public void publish( Data dataMachine) {
        getMediator().update(dataMachine);
    }

    //sensor atualizou
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

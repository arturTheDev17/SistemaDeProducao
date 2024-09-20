package machine;

import data.DataLathe;
import data.Data;
import java.text.DecimalFormat;

public class Lathe extends Machine {


    public Lathe() {
    }

    public Lathe(DataLathe data) {
        this.data = data;
    }

    private DataLathe data;

    @Override
    public void publish( Data data ) {
        getMediator().update( data );
    }

    //sensor atualizou
    @Override
    public void changeData(Data data) {
        //Round the data numbers
        data = (DataLathe) data;

        DecimalFormat df2 = new DecimalFormat("#,00");
        data.setTemperature(Double.parseDouble(df2.format(data.getTemperature())));

        if (this.data != null) {
            String machineName = this.data.getMachineName();
            this.data = (DataLathe) data;
            this.data.setMachineName(machineName);
        } else {
            this.data = (DataLathe) data;
        }

        publish(this.data);
    }

}

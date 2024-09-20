package machine;

import data.Data;
import data.DataLathe;
import data.DataWelder;

import java.text.DecimalFormat;

public class Welder extends Machine {

    public Welder() {

    }

    public Welder(DataWelder data) {
        this.data = data;
    }

    private DataWelder data;

    @Override
    public void publish( Data data ) {
        getMediator().update( data );
    }

    @Override
    public void changeData(Data data) {
        DecimalFormat df2 = new DecimalFormat("#,00");
        data.setTemperature(Double.parseDouble(df2.format(data.getTemperature())));
        ((DataWelder) data).setActiveTime(Double.parseDouble(df2.format(((DataWelder) data).getActiveTime())));
        ((DataWelder) data).setCurrent(Double.parseDouble(df2.format(((DataWelder) data).getCurrent())));

        if (this.data != null) {
            String machineName = this.data.getMachineName();
            this.data = (DataWelder) data;
            this.data.setMachineName(machineName);
        } else {
            this.data = (DataWelder) data;
        }
        publish(this.data);
    }

}

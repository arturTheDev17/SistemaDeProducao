package machine;

import data.Data;
import data.DataLathe;
import data.DataWelder;

import java.text.DecimalFormat;

public class Welder extends Machine {

    private DataWelder data;

    @Override
    public void publish( Data data ) {
        getMediator().update( data ); //eu consigo chamar o pblish pelo machine, por conta d ter o objeto comigo
    }

    @Override
    public void changeData(Data data) {
        //Round the data numbers
        data = (DataWelder) data;

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

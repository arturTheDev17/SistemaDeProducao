package machine;

import data.Data;
import data.DataLathe;
import data.DataWelder;

public class Welder extends Machine {

    private DataWelder data;

    @Override
    public void publish( Data data ) {
        getMediator().update( data ); //eu consigo chamar o pblish pelo machine, por conta d ter o objeto comigo
    }

    @Override
    public void changeData(Data data) {
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

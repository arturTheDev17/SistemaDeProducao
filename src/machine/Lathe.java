package machine;

import data.DataLathe;
import data.Data;

public class Lathe extends Machine {

    private DataLathe data;

    @Override
    public void publish( Data data ) {
        getMediator().update( data );
    }

    //sensor atualizou
    @Override
    public void changeData(Data data) {
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

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
        this.data = (DataLathe) data;
        publish(this.data);
    }

}

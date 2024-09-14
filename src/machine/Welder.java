package machine;

import data.Data;
import data.DataWelder;

public class Welder extends Machine {

    private DataWelder data;

    @Override
    public void publish( Data data ) {
        getMediator().update( data ); //eu consigo chamar o pblish pelo machine, por conta d ter o objeto comigo
    }

    @Override
    public void changeData(Data data) {
        this.data = (DataWelder) data;
        publish(this.data);
    }

}

package panel;

import data.Data;
import structure.ObserverString;

public class InformationPanel implements ObserverString {

    @Override
    public void update(String data) {
        showData(data);
    }


    public void showData(String data) {
        System.out.println(data);
    }

}

package structure;
import data.Data;
import data.DataMachine;

public interface Observable {

    void subscribe(Observer observer);

    void publish(Data data);

    void unsubscribe(Observer observer);
}

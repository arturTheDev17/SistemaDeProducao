package structure;
import data.Data;

public interface ObservableData {

    void subscribe(ObserverData observer);

    void publish(Data data);

    void unsubscribe(ObserverData observer);
}

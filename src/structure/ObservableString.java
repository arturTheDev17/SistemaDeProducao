package structure;
import data.Data;
public interface ObservableString {

    void subscribe(ObserverString observer);

    void publish(String data);
    void unsubscribe(ObserverString observer);
}

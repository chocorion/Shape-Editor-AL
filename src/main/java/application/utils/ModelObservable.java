package application.utils;

public interface ModelObservable {
    void attachObserver(ModelObserver obs);
    void dettachObserver(ModelObserver obs);

    void notifyObserver();
}

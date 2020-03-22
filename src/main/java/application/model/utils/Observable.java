package application.model.utils;

public interface Observable {
    void attachObserver(Observer obs);
    void dettachObserver(Observer obs);

    void notifyObserver();
}

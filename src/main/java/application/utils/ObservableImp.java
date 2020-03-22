package application.utils;

import java.util.ArrayList;

public class ObservableImp implements Observable {
    private ArrayList<Observer> observers;

    public ObservableImp() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void attachObserver(Observer obs) {
        this.observers.add(obs);
    }

    @Override
    public void dettachObserver(Observer obs) {
        this.observers.remove(obs);
    }

    @Override
    public void notifyObserver() {
        for (Observer obs : this.observers) {
            obs.update();
        }
    }
}

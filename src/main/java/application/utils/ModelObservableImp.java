package application.utils;

import java.util.ArrayList;

public class ModelObservableImp implements ModelObservable {
    private ArrayList<ModelObserver> modelObservers;

    public ModelObservableImp() {
        this.modelObservers = new ArrayList<>();
    }

    @Override
    public void attachObserver(ModelObserver obs) {
        this.modelObservers.add(obs);
    }

    @Override
    public void dettachObserver(ModelObserver obs) {
        this.modelObservers.remove(obs);
    }

    @Override
    public void notifyObserver() {
        for (ModelObserver obs : this.modelObservers) {
            obs.update();
        }
    }
}

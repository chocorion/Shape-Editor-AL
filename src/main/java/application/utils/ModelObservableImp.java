package application.utils;

import java.util.ArrayList;

/**
 * Represent a basic implementation of ModelObservable interface.
 */
public class ModelObservableImp implements ModelObservable {
    private ArrayList<ModelObserver> modelObservers;

    /**
     * Default constructor.
     */
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
        ArrayList<ModelObserver> copy = (ArrayList<ModelObserver>) modelObservers.clone();

        for (ModelObserver obs : copy) {
            obs.update();
        }
    }
}

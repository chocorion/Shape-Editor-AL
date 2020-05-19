package application.utils;

/**
 * Interface for an observable element in the model.
 */
public interface ModelObservable {
    /**
     * Add an observer.
     * @param obs The observer to add.
     */
    void attachObserver(ModelObserver obs);


    /**
     * Detach an observer.
     * @param obs The observer to remove.
     */
    void dettachObserver(ModelObserver obs);


    /**
     * Notify all the attached observer.
     */
    void notifyObserver();
}

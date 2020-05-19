package application.utils;


/**
 * Basic interface for model observer.
 */
public interface ModelObserver {
    /**
     * Methods called by observable on event.
     */
    void update();
}

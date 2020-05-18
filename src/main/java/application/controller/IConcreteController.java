package application.controller;

/**
 * @brief Represent a concrete controller for the application.
 */
public interface IConcreteController {
    /**
     * @brief Methods that the application call at each turn.
     * @return false if the application has ended, else false.
     */
    boolean tick();
}

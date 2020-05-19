package application.ui;

import application.controller.IConcreteController;
import application.controller.MainController;
import application.model.Model;
import application.view.IConcreteView;

/**
 * Represent a factory that allow to get the current implementation for view and controller.
 */
public interface UIFactory {
    /**
     * Return an implementation for the view.
     * @param model Current model, used by the view.
     * @return Implementation of the view.
     */
    IConcreteView getView(Model model);


    /**
     * Return an implementation for the controller.
     * @param mainController Current mainController to use in the implementation.
     * @param view Concrete view to use.
     * @return Implementation of the controller.
     */
    IConcreteController getController(MainController mainController, IConcreteView view);
}

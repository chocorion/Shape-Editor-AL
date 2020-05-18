package application.ui;

import application.controller.IConcreteController;
import application.controller.MainController;
import application.model.Model;
import application.view.IConcreteView;

public interface UIFactory {
    IConcreteView getView(Model model);
    IConcreteController getController(MainController mainController, IConcreteView view);
}

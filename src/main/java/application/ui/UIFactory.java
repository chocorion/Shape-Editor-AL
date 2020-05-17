package application.ui;

import application.controller.ConcreteControllerItf;
import application.controller.MainController;
import application.model.Model;
import application.view.IConcreteView;

public interface UIFactory {
    IConcreteView getView(Model model);
    ConcreteControllerItf getController(MainController mainController, IConcreteView view);
}

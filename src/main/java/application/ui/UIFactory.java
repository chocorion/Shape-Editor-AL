package application.ui;

import application.controller.ConcreteControllerItf;
import application.controller.MainController;
import application.model.Model;
import application.view.ConcreteViewItf;

public interface UIFactory {
    ConcreteViewItf getView(Model model);
    ConcreteControllerItf getController(MainController mainController, ConcreteViewItf view);
}

package application.ui;

import application.controller.Controller;
import application.controller.MainController;
import application.model.Model;
import application.view.ConcreteViewItf;

public interface UIFactory {
    ConcreteViewItf getView(Model model);
    Controller getController(MainController mainController, ConcreteViewItf view);
}

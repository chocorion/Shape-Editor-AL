package application.controller;

import application.model.Model;
import application.ui.javafx.ViewFx;
import application.view.ConcreteViewItf;
import application.view.View;

public class MainController {
    private Model model;
    private View view;
    private Controller controllerImp;

    public MainController(Model model, View view, ConcreteViewItf viewImp) {
        this.model = model;
        this.view = view;

        this.controllerImp = new ControllerFx(this, (ViewFx) viewImp);
        ((ViewFx) viewImp).AddController(this.controllerImp);
    }

}

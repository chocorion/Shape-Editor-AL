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

    public void onLeftClickPressed(double x, double y) {
        System.out.println("Left Click pressed on " + x + " " + y);
    }

    public void onRightClickPressed(double x, double y) {
        System.out.println("Right Click pressed on " + x + " " + y);

    }

    public void onLeftClickReleased(double x, double y) {
        System.out.println("Left Click released on " + x + " " + y);
    }

    public void onRightClickReleased(double x, double y) {
        System.out.println("Right Click released on " + x + " " + y);
    }

    public void onMouseDragged(double x, double y) {
        System.out.println("Mouse dragged on " + x + " " + y);
    }

}

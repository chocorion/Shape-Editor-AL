package application.controller;

import application.controller.states.*;
import application.model.Model;
import application.ui.javafx.ControllerFx;
import application.ui.javafx.ViewFx;
import application.view.ConcreteViewItf;
import application.view.MainView;

public class MainController {
    private Model model;
    private MainView view;
    private Controller controllerImp;


    private ControllerState currentState;


    public MainController(Model model, MainView view, ConcreteViewItf viewImp) {
        this.model = model;
        this.view = view;


        this.controllerImp = new ControllerFx(this, (ViewFx) viewImp);
        ((ViewFx) viewImp).AddController(this.controllerImp);


        DefaultState.setInstance(this, model, view);
        WhiteBoardMenuState.setInstance(this, model, view);
        SelectionState.setInstance(this, model, view);
        ShapeHoldingState.setInstance(this, model, view);

        currentState = DefaultState.getInstance();
    }

    public void switchState(ControllerState newState) {
        System.out.println("[MainController] Switche to state : " + newState);
        currentState = newState;
    }

    public void onLeftClickPressed(double x, double y) {
        while (!currentState.onLeftClickPressed((int) x, (int) y));
    }

    public void onRightClickPressed(double x, double y) {
        while (!currentState.onRightClickPressed((int) x, (int) y));
    }

    public void onLeftClickReleased(double x, double y) {
        while (!currentState.onLeftClickReleased((int) x, (int) y));
    }

    public void onRightClickReleased(double x, double y) {
        while (!currentState.onRightClickReleased((int) x, (int) y));
    }

    public void onMouseDragged(double x, double y) {
        while (!currentState.onMouseDragged((int) x, (int) y));
    }
}



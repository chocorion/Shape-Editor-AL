package application.controller;

import application.controller.states.*;
import application.model.Model;
import application.view.MainView;

import java.util.ArrayList;

public class MainController {
    private Model model;
    private MainView view;

    private ArrayList<String> keysPressed;


    private ControllerState currentState;

    private int lastMouseX, lastMouseY;

    public MainController(Model model, MainView view) {
        this.model = model;
        this.view = view;

        keysPressed = new ArrayList<>();
        lastMouseX = lastMouseY = 0;

        DefaultState.setInstance(this, model, view);
        WhiteBoardMenuState.setInstance(this, model, view);
        SelectionState.setInstance(this, model, view);
        ShapeHoldingState.setInstance(this, model, view);
        MovingShape.setInstance(this, model, view);
        EditionMenuState.setInstance(this, model, view);

        currentState = DefaultState.getInstance();
    }

    public void switchState(ControllerState newState) {
        System.out.println("[MainController] Switch state to " + newState.toString());
        currentState = newState;
        newState.onSwitch();
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

    public void onMouseMoved(double x, double y) {
        lastMouseX = (int) x;
        lastMouseY = (int) y;

        while (!currentState.onMouseMoved(lastMouseX, lastMouseY));
    }

    public void onWindowsResize(int width, int height) {
        view.changeSize(width, height);
    }

    public void onKeyPressed(String key) {
        if (!keysPressed.contains(key)) {
            keysPressed.add(key);
        }

        while (!currentState.onKeyPressed(key, lastMouseX, lastMouseY));
    }

    public void onKeyReleased(String key) {
        keysPressed.remove(key);

        while (!currentState.onKeyReleased(key, lastMouseX, lastMouseY));
    }

    public boolean isKeyPressed(String keyCode) {
        return keysPressed.contains(keyCode);
    }
}



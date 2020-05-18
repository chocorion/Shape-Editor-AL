package application.controller;

import application.controller.states.*;
import application.model.Model;
import application.view.MainView;

import java.util.ArrayList;


/**
 * This class must receive the inputs from the concrete controller,
 * and acts on the application according to its state.
 */
public class MainController {
    private Model model;
    private MainView view;

    private final ArrayList<String> keysPressed;

    private ControllerState currentState;

    private int lastMouseX, lastMouseY;

    /**
     * Create a mainController for a specific model and view.
     * @param model The mainController's model.
     * @param view The mainController's view.
     */
    public MainController(Model model, MainView view) {
        this.model = model;
        this.view = view;

        keysPressed = new ArrayList<>();
        lastMouseX = lastMouseY = 0;

        // Init the instance of all state of the mainController.
        DefaultState.setInstance(this, model, view);
        WhiteBoardMenuState.setInstance(this, model, view);
        SelectionState.setInstance(this, model, view);
        ShapeHoldingState.setInstance(this, model, view);
        MovingShape.setInstance(this, model, view);
        EditionMenuState.setInstance(this, model, view);

        currentState = DefaultState.getInstance();
    }


    /**
     * Switch the current state, and call the onSwitch state methods.
     * @param newState The new current state of mainController.
     */
    public void switchState(ControllerState newState) {
        currentState = newState;
        newState.onSwitch();
    }


    /**
     * React to left click pressed.
     * @param x The absolute x coords on windows.
     * @param y The absolute y coords on windows.
     */
    public void onLeftClickPressed(double x, double y) {
        while (!currentState.onLeftClickPressed((int) x, (int) y));
    }


    /**
     * React to right click pressed.
     * @param x The absolute x coords on windows.
     * @param y The absolute y coords on windows.
     */
    public void onRightClickPressed(double x, double y) {
        while (!currentState.onRightClickPressed((int) x, (int) y));
    }


    /**
     * React to left click released.
     * @param x The absolute x coords on windows.
     * @param y The absolute y coords on windows.
     */
    public void onLeftClickReleased(double x, double y) {
        while (!currentState.onLeftClickReleased((int) x, (int) y));
    }


    /**
     * React to right click released.
     * @param x The absolute x coords on windows.
     * @param y The absolute y coords on windows.
     */
    public void onRightClickReleased(double x, double y) {
        while (!currentState.onRightClickReleased((int) x, (int) y));
    }


    /**
     * React mouse movement.
     * @param x The absolute x coords on windows.
     * @param y The absolute y coords on windows.
     */
    public void onMouseMoved(double x, double y) {
        lastMouseX = (int) x;
        lastMouseY = (int) y;

        while (!currentState.onMouseMoved(lastMouseX, lastMouseY));
    }


    /**
     * React to windows resize.
     * @param width The new width of the window.
     * @param height The new height of the window.
     */
    public void onWindowsResize(int width, int height) {
        view.changeSize(width, height);
    }


    /**
     * React to key pressed.
     * @param key The code of the key pressed.
     */
    public void onKeyPressed(String key) {
        if (!keysPressed.contains(key)) {
            keysPressed.add(key);
        }

        while (!currentState.onKeyPressed(key, lastMouseX, lastMouseY));
    }


    /**
     * React to key released.
     * @param key The code of the key pressed.
     */
    public void onKeyReleased(String key) {
        keysPressed.remove(key);

        while (!currentState.onKeyReleased(key, lastMouseX, lastMouseY));
    }


    /**
     * Return if a key is pressed, or not.
     * @param keyCode The code of the key.
     * @return True if the key is currently pressed, else false.
     */
    public boolean isKeyPressed(String keyCode) {
        return keysPressed.contains(keyCode);
    }
}



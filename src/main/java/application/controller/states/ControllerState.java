package application.controller.states;

/**
 * Represent a state of the mainController.
 */
public interface ControllerState {
    /**
     * Method called when left click is pressed.
     * @param x The absolute x coords on the windows.
     * @param y The absolute y coords on the windows.
     * @return True if the input was handle, else false.
     */
    boolean onLeftClickPressed(int x, int y);


    /**
     * Method called when left click is released.
     * @param x The absolute x coords on the windows.
     * @param y The absolute y coords on the windows.
     * @return True if the input was handle, else false.
     */
    boolean onLeftClickReleased(int x, int y);


    /**
     * Method called when right click is pressed.
     * @param x The absolute x coords on the windows.
     * @param y The absolute y coords on the windows.
     * @return True if the input was handle, else false.
     */
    boolean onRightClickPressed(int x, int y);


    /**
     * Method called when right click is released.
     * @param x The absolute x coords on the windows.
     * @param y The absolute y coords on the windows.
     * @return True if the input was handle, else false.
     */
    boolean onRightClickReleased(int x, int y);


    /**
     * Method called when mouse move.
     * @param x The absolute x coords on the windows.
     * @param y The absolute y coords on the windows.
     * @return True if the input was handle, else false.
     */
    boolean onMouseMoved(int x, int y);


    /**
     * Method called when key is pressed.
     * @param keyCode The code of the key.
     * @param mouseX The absolute x position of the mouse when the key was pressed.
     * @param mouseY The absolute y position of the mouse when the key was pressed.
     * @return True if the input was handle, else false.
     */
    boolean onKeyPressed(String keyCode, int mouseX, int mouseY);


    /**
     * Method called when key is released.
     * @param keyCode The code of the key.
     * @param mouseX The absolute x position of the mouse when the key was released.
     * @param mouseY The absolute y position of the mouse when the key was released.
     * @return True if the input was handle, else false.
     */
    boolean onKeyReleased(String keyCode, int mouseX, int mouseY);

    /**
     * Method called when the mainController switch to this state.
     */
    void onSwitch();
}

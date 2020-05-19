package application.view.element.interaction;

/**
 * Basic interaction available for an element.
 * All methods must returns false if the element loose the focus.
 */
public class Interaction {
    /**
     * Action when the left click is pressed.
     * @param x X coords of the click.
     * @param y Y coords of the click.
     * @return true if the element keep the focus, else false.
     */
    public boolean onLeftClickPressed(int x, int y) {
        return true;
    }


    /**
     * Action when the left click is released.
     * @param x X coords of the click.
     * @param y Y coords of the click.
     * @return true if the element keep the focus, else false.
     */
    public boolean onLeftClickReleased(int x, int y) {
        return true;
    }


    /**
     * Action when the right click is pressed.
     * @param x X coords of the click.
     * @param y Y coords of the click.
     * @return true if the element keep the focus, else false.
     */
    public boolean onRightClickPressed(int x, int y) {
        return true;
    }


    /**
     * Action when the right click is released.
     * @param x X coords of the click.
     * @param y Y coords of the click.
     * @return true if the element keep the focus, else false.
     */
    public boolean onRightClickReleased(int x, int y) {
        return true;
    }


    /**
     * Action when the mouse moves, dragged or not.
     * @param x X coords of the mouse.
     * @param y Y coords of the mouse.
     * @return true if the element keep the focus, else false.
     */
    public boolean onMouseMoved(int x, int y) {
        return true;
    }


    /**
     * Action when a key is pressed.
     * @param keyCode Code of the pressed key.
     * @param mouseX X coords of the mouse when the key was pressed.
     * @param mouseY Y coords of the mouse when the key was pressed.
     * @return true if the element keep the focus, else false.
     */
    public boolean onKeyPressed(String keyCode, int mouseX, int mouseY) {
        return true;
    }


    /**
     * Action when a key is released.
     * @param keyCode Code of the released key.
     * @param mouseX X coords of the mouse when the key was released.
     * @param mouseY Y coords of the mouse when the key was released.
     * @return true if the element keep the focus, else false.
     */
    public boolean onKeyReleased(String keyCode, int mouseX, int mouseY) {
        return true;
    }
}

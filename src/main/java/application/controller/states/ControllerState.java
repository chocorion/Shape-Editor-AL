package application.controller.states;

import application.controller.Controller;

public interface ControllerState {
    boolean onLeftClickPressed(int x, int y);
    boolean onLeftClickReleased(int x, int y);
    boolean onRightClickPressed(int x, int y);
    boolean onRightClickReleased(int x, int y);
    boolean onMouseDragged(int x, int y);
    boolean onKeyPressed(String keyCode, int mouseX, int mouseY);
    boolean onKeyReleased(String keyCode, int mouseX, int mouseY);
}

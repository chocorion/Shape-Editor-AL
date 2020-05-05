package application.controller.states;

public class ControllerStateImp implements ControllerState {
    @Override
    public boolean onLeftClickPressed(int x, int y) {
        return true;
    }

    @Override
    public boolean onLeftClickReleased(int x, int y) {
        return true;
    }

    @Override
    public boolean onRightClickPressed(int x, int y) {
        return true;
    }

    @Override
    public boolean onRightClickReleased(int x, int y) {
        return true;
    }

    @Override
    public boolean onMouseDragged(int x, int y) {
        return true;
    }

    @Override
    public boolean onKeyPressed(String keyCode, int mouseX, int mouseY) {
        return true;
    }

    @Override
    public boolean onKeyReleased(String keyCode, int mouseX, int mouseY) {
        return true;
    }
}

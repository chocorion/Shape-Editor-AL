package application.controller.states.substates;

import application.controller.MainController;
import application.controller.states.ControllerStateImp;
import application.model.Model;
import application.model.shape.Shape;
import application.view.MainView;
import application.view.menu.EditionMenu;
import application.view.menu.SubMenuRotate;

public class SubMenuRotationState extends ControllerStateImp {
    private static SubMenuRotationState state;

    MainController mainController;
    Model model;
    MainView view;

    EditionMenu menu;

    int sliderId;

    private SubMenuRotationState(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;

        menu = view.getWhiteBoard().getEditionMenu();

        sliderId = -1;
    }

    public static void setInstance(MainController mainController, Model model, MainView view) {
        state = new SubMenuRotationState(mainController, model, view);
    }


    public static SubMenuRotationState getInstance() {
        return state;
    }

    @Override
    public boolean onLeftClickReleased(int x, int y) {
        if (sliderId != -1) {
            sliderId = -1;
            return true;
        }

        return false;
    }

    @Override
    public boolean onLeftClickPressed(int x, int y) {
        sliderId = ((SubMenuRotate) menu.getSelectedMenu()).getSliderId(x, y);

        return true;
    }

    @Override
    public boolean onMouseMoved(int x, int y) {
        if (sliderId != -1) {
            ((SubMenuRotate) menu.getSelectedMenu()).moveSlider(x, y, sliderId);
            double value = ((SubMenuRotate) menu.getSelectedMenu()).getValue(sliderId);
            value = 360 * value - 180;

            for (Shape s : view.getWhiteBoard().getSelectedShapes()) {
                s.setAngle(value);
            }
            view.getWhiteBoard().update();

            return true;
        }

        return false;
    }

    @Override
    public void onSwitch() {
        menu = view.getWhiteBoard().getEditionMenu();
    }

    @Override
    public String toString() {
        return "submenu color";
    }
}

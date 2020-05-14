package application.controller.states.substates;


import application.controller.MainController;
import application.controller.states.ControllerStateImp;
import application.controller.states.EditionMenuState;
import application.model.Model;
import application.view.MainView;
import application.view.menu.EditionMenu;
import application.view.menu.SubMenuResizeRectangle;


public class SubMenuResizeState extends ControllerStateImp {
    private static SubMenuResizeState state;

    MainController mainController;
    Model model;
    MainView view;

    EditionMenu menu;

    int sliderId;

    private SubMenuResizeState(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;

        menu = view.getWhiteBoard().getEditionMenu();

        sliderId = -1;
    }

    public static void setInstance(MainController mainController, Model model, MainView view) {
        state = new SubMenuResizeState(mainController, model, view);
    }


    public static SubMenuResizeState getInstance() {
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
        sliderId = ((SubMenuResizeRectangle) menu.getSelectedMenu()).getSliderId(x, y);

        return true;
    }

    @Override
    public boolean onMouseMoved(int x, int y) {
        if (sliderId != -1) {
            ((SubMenuResizeRectangle) menu.getSelectedMenu()).moveSlider(x, y, sliderId);
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
        return "submenu resize";
    }
}

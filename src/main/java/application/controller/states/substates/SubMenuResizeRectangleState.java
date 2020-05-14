package application.controller.states.substates;


import application.controller.MainController;
import application.controller.states.ControllerStateImp;
import application.model.Model;
import application.model.shape.Rectangle;
import application.view.MainView;
import application.view.menu.EditionMenu;
import application.view.menu.SubMenuResizeRectangle;


public class SubMenuResizeRectangleState extends ControllerStateImp {
    private static SubMenuResizeRectangleState state;

    private double originalWidth;
    private double originalHeight;


    MainController mainController;
    Model model;
    MainView view;

    EditionMenu menu;
    Rectangle rect;

    int sliderId;

    private SubMenuResizeRectangleState(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;

        menu = view.getWhiteBoard().getEditionMenu();

        sliderId = -1;
    }

    public static void setInstance(MainController mainController, Model model, MainView view) {
        state = new SubMenuResizeRectangleState(mainController, model, view);
    }


    public static SubMenuResizeRectangleState getInstance() {
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
            double value = ((SubMenuResizeRectangle) menu.getSelectedMenu()).getSliderValue(sliderId);

            value *= 2;



            if (sliderId == 0) {
                rect.setWidth(originalWidth * value);
            } else if (sliderId == 1) {
                rect.setHeight(originalHeight * value);
            }
            model.getWhiteBoard().update();

            return true;
        }

        return false;
    }

    @Override
    public void onSwitch() {
        menu = view.getWhiteBoard().getEditionMenu();
        rect = (Rectangle) view.getWhiteBoard().getSelectedShapes().iterator().next();

        originalWidth = rect.getWidth();
        originalHeight = rect.getHeight();
    }

    @Override
    public String toString() {
        return "submenu resize";
    }
}

package application.controller.states.substates;


import application.controller.MainController;
import application.controller.states.ControllerStateImp;
import application.model.Model;
import application.model.shape.Shape;
import application.utils.Color;
import application.view.MainView;
import application.view.menu.EditionMenu;
import application.view.menu.SubMenuColor;

/**
 * Represent the controls of the color sub menu in edition menu.
 */
public class SubMenuColorState extends ControllerStateImp {
    private static SubMenuColorState state;

    MainController mainController;
    Model model;
    MainView view;

    EditionMenu menu;

    int sliderId;

    /**
     * Parameterized constructor.
     * @param mainController The current MainController.
     * @param model The current model of the application.
     * @param view The MainVew of the application
     */
    private SubMenuColorState(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;

        menu = view.getWhiteBoard().getEditionMenu();

        sliderId = -1;
    }


    /**
     * Set the instance.
     * @param mainController The current MainController.
     * @param model The current model of the application.
     * @param view The MainVew of the application
     */
    public static void setInstance(MainController mainController, Model model, MainView view) {
        state = new SubMenuColorState(mainController, model, view);
    }


    /**
     * Return the current instance.
     * @return Current instance.
     */
    public static SubMenuColorState getInstance() {
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
        sliderId = ((SubMenuColor) menu.getSelectedMenu()).getSliderId(x, y);

        return true;
    }


    @Override
    public boolean onMouseMoved(int x, int y) {
        if (sliderId != -1) {
            ((SubMenuColor) menu.getSelectedMenu()).moveSlider(x, y, sliderId);

            Color c = ((SubMenuColor) menu.getSelectedMenu()).getColor();
            for (Shape s : view.getWhiteBoard().getSelectedShapes()) {
                s.setColor(c);
            }
            view.getWhiteBoard().update();

            return true;
        }

        return false;
    }


    @Override
    public void onSwitch() {
        menu = view.getWhiteBoard().getEditionMenu();
        setSlidersToShapeColor();
    }


    /**
     * Set all the sliders to the good value for current shape color.
     */
    private void setSlidersToShapeColor() {
        Shape currentShape = view.getWhiteBoard().getSelectedShapes().iterator().next();
        Color currentColor = currentShape.getColor();

        ((SubMenuColor) menu.getSelectedMenu()).moveSlider(currentColor.getR(), 0);
        ((SubMenuColor) menu.getSelectedMenu()).moveSlider(currentColor.getG(), 1);
        ((SubMenuColor) menu.getSelectedMenu()).moveSlider(currentColor.getB(), 2);

    }


    @Override
    public String toString() {
        return "submenu color";
    }
}

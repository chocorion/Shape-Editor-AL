package application.controller.states.substates;

import application.controller.MainController;
import application.controller.states.ControllerStateImp;
import application.model.Model;
import application.model.shape.CompositeShape;
import application.model.shape.Shape;
import application.view.MainView;
import application.view.element.interaction.Interaction;
import application.view.menu.EditionMenu;
import application.view.menu.SubMenuResizeGlobal;

/**
 * Represent the controls of the resize sub menu in edition menu.
 */
public class SubMenuResizeGlobalState extends ControllerStateImp {
    private static SubMenuResizeGlobalState state;

    private double lastFactor;

    private static double maxFactor = 10.;


    MainController mainController;
    Model model;
    MainView view;

    Interaction interaction;

    EditionMenu menu;
    SubMenuResizeGlobal subMenu;
    Shape shape;

    private double originalWidth;

    int inputId;

    /**
     * Parameterized constructor.
     * @param mainController The current MainController.
     * @param model The current model of the application.
     * @param view The MainVew of the application
     */
    private SubMenuResizeGlobalState(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;

        menu = view.getWhiteBoard().getEditionMenu();


        inputId = -1;
    }


    /**
     * Set the instance.
     * @param mainController The current MainController.
     * @param model The current model of the application.
     * @param view The MainVew of the application
     */
    public static void setInstance(MainController mainController, Model model, MainView view) {
        state = new SubMenuResizeGlobalState(mainController, model, view);
    }


    /**
     * Return the current instance.
     * @return Current instance.
     */
    public static SubMenuResizeGlobalState getInstance() {
        return state;
    }


    @Override
    public boolean onLeftClickPressed(int x, int y) {
        if (interaction != null) {
            if (!interaction.onLeftClickPressed(x, y))  interaction = null;
            else return true;
        }

        inputId = subMenu.getInputId(x, y);
        interaction = subMenu.getInteraction(inputId);

        return true;
    }


    @Override
    public boolean onKeyPressed(String keyCode, int mouseX, int mouseY) {
        if (interaction != null) {
            if (!interaction.onKeyPressed(keyCode, mouseX, mouseY)) {
                interaction = null;
            }

            try {
                double value = Double.parseDouble(subMenu.getText(inputId));

                if (value > 0 && value < maxFactor) {
                    if (inputId == 0) {
                        shape.resize(originalWidth/ shape.getWidth());
                        shape.resize(value);
                    }

                }
            } catch (Exception ignored) {}

            model.getWhiteBoard().update();
        }

        return true;
    }


    @Override
    public void onSwitch() {
        menu = view.getWhiteBoard().getEditionMenu();
        subMenu = (SubMenuResizeGlobal) menu.getSelectedMenu();
        shape = view.getWhiteBoard().getSelectedShapes().iterator().next();

        originalWidth = shape.getWidth();
    }


    @Override
    public String toString() {
        return "submenu resize composite";
    }
}

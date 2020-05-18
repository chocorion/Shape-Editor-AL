package application.controller.states.substates;

import application.controller.MainController;
import application.controller.states.ControllerStateImp;
import application.model.Model;
import application.model.shape.Rectangle;
import application.view.MainView;
import application.view.element.interaction.Interaction;
import application.view.menu.EditionMenu;
import application.view.menu.SubMenuRound;

/**
 * Represent the controls of the rectangle round sub menu in edition menu.
 */
public class SubMenuRoundState extends ControllerStateImp {
    private static SubMenuRoundState state;


    MainController mainController;
    Model model;
    MainView view;

    Interaction interaction;

    EditionMenu menu;
    SubMenuRound subMenu;
    Rectangle rect;

    int inputId;

    /**
     * Parameterized constructor.
     * @param mainController The current MainController.
     * @param model The current model of the application.
     * @param view The MainVew of the application
     */
    private SubMenuRoundState(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;

        menu = view.getWhiteBoard().getEditionMenu();


        inputId = -1;
    }


    /**
     * Set the current instance.
     * @param mainController The current MainController.
     * @param model The current model of the application.
     * @param view The MainVew of the application
     */
    public static void setInstance(MainController mainController, Model model, MainView view) {
        state = new SubMenuRoundState(mainController, model, view);
    }


    /**
     * Return the current instance.
     * @return Current instance.
     */
    public static SubMenuRoundState getInstance() {
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
                int value = Integer.parseInt(subMenu.getText(inputId));

                if (value >= 0) {
                    if (inputId == 0) {
                        rect.setRoundValue(value);
                        model.getWhiteBoard().update();
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
        subMenu = (SubMenuRound) menu.getSelectedMenu();
        rect = (Rectangle) view.getWhiteBoard().getSelectedShapes().iterator().next();
    }


    @Override
    public String toString() {
        return "submenu round";
    }
}

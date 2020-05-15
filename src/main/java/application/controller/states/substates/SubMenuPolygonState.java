package application.controller.states.substates;

import application.controller.MainController;
import application.controller.states.ControllerStateImp;
import application.model.Model;
import application.model.shape.Polygon;
import application.view.MainView;
import application.view.element.interaction.Interaction;
import application.view.menu.EditionMenu;
import application.view.menu.SubMenuPolygon;

public class SubMenuPolygonState extends ControllerStateImp {
    private static SubMenuPolygonState state;

    MainController mainController;
    Model model;
    MainView view;

    Interaction interaction;

    EditionMenu menu;
    SubMenuPolygon subMenu;
    Polygon polygon;

    int inputId;

    private SubMenuPolygonState(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;

        menu = view.getWhiteBoard().getEditionMenu();


        inputId = -1;
    }

    public static void setInstance(MainController mainController, Model model, MainView view) {
        state = new SubMenuPolygonState(mainController, model, view);
    }


    public static SubMenuPolygonState getInstance() {
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

                if (value > 2 ) {
                    if (inputId == 0) {
                        polygon.setNumberSide(value);
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
        subMenu = (SubMenuPolygon) menu.getSelectedMenu();
        polygon = (Polygon) view.getWhiteBoard().getSelectedShapes().iterator().next();
    }

    @Override
    public String toString() {
        return "submenu resize composite";
    }
}

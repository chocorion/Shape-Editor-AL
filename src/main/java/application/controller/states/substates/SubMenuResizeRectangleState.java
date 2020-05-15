package application.controller.states.substates;


import application.controller.MainController;
import application.controller.states.ControllerStateImp;
import application.model.Model;
import application.model.shape.Rectangle;
import application.view.MainView;
import application.view.element.interaction.Interaction;
import application.view.menu.EditionMenu;
import application.view.menu.SubMenuResizeRectangle;


public class SubMenuResizeRectangleState extends ControllerStateImp {
    private static SubMenuResizeRectangleState state;

    private double originalWidth;
    private double originalHeight;

    private static double maxFactor = 10.;


    MainController mainController;
    Model model;
    MainView view;

    Interaction interaction;

    EditionMenu menu;
    SubMenuResizeRectangle subMenu;
    Rectangle rect;

    int inputId;

    private SubMenuResizeRectangleState(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;

        menu = view.getWhiteBoard().getEditionMenu();


        inputId = -1;
    }

    public static void setInstance(MainController mainController, Model model, MainView view) {
        state = new SubMenuResizeRectangleState(mainController, model, view);
    }


    public static SubMenuResizeRectangleState getInstance() {
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
                    if (inputId == 0)
                        rect.setWidth(originalWidth * value);

                    else if (inputId == 1)
                        rect.setHeight(originalHeight * value);
                }
            } catch (Exception ignored) {}

            model.getWhiteBoard().update();
        }

        return true;
    }

    @Override
    public void onSwitch() {
        menu = view.getWhiteBoard().getEditionMenu();
        subMenu = (SubMenuResizeRectangle) menu.getSelectedMenu();
        rect = (Rectangle) view.getWhiteBoard().getSelectedShapes().iterator().next();

        originalWidth = rect.getWidth();
        originalHeight = rect.getHeight();
    }

    @Override
    public String toString() {
        return "submenu resize";
    }
}

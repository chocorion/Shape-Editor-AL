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
        inputId = ((SubMenuResizeRectangle) menu.getSelectedMenu()).getInputId(x, y);
        System.out.println("[resize] press on input, id -> " + inputId);

        return true;
    }

    @Override
    public boolean onKeyPressed(String keyCode, int mouseX, int mouseY) {
        if (inputId != -1) {
            System.out.println("Press key !");
            // ENTER, BACK_SPACE
            ((SubMenuResizeRectangle) menu.getSelectedMenu()).addText(inputId, keyCode);
            view.getWhiteBoard().update();
        }

        return true;
    }

    @Override
    public boolean onKeyReleased(String keyCode, int mouseX, int mouseY) {
        return true;
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

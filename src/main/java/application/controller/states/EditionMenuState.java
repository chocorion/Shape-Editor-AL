package application.controller.states;

import application.controller.MainController;
import application.controller.states.substates.*;
import application.model.Model;
import application.model.command.concreteCommand.Replace;
import application.model.shape.Shape;
import application.view.MainView;
import application.view.menu.EditionMenu;
import application.view.menu.SubMenuResizeGlobal;
import application.view.menu.SubMenuResizeRectangle;

import java.util.HashSet;

public class EditionMenuState extends ControllerStateImp {
    private static EditionMenuState state;

    private ControllerState subState;

    MainController mainController;
    Model model;
    MainView view;

    EditionMenu menu;

    HashSet<Shape> shapeSave;

    int buttonId;
    boolean moving;
    int mouseX, mouseY;

    private EditionMenuState(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;

        shapeSave = new HashSet<>();

        buttonId = -1;
        moving = false;

        SubMenuResizeRectangleState.setInstance(mainController, model, view);
        SubMenuColorState.setInstance(mainController, model, view);
        SubMenuResizeGlobalState.setInstance(mainController, model, view);
        SubMenuRotationState.setInstance(mainController, model, view);
        SubMenuPolygonState.setInstance(mainController, model, view);
        SubMenuRoundState.setInstance(mainController, model, view);

        // By default
        subState = SubMenuColorState.getInstance();
    }

    public static void setInstance(MainController mainController, Model model, MainView view) {
        state = new EditionMenuState(mainController, model, view);
    }


    public static EditionMenuState getInstance() {
        return state;
    }

    @Override
    public boolean onLeftClickReleased(int x, int y) {
        moving = false;

        if (buttonId != -1) {
            view.getWhiteBoard().getEditionMenu().unpushButton(buttonId);
            buttonId = -1;
            return true;
        }

        if (subState.onLeftClickReleased(x, y)) {
            return true;
        }

        if (!menu.isIn(x, y)) {
            reset();
            view.getWhiteBoard().closeEditionMenu();
            mainController.switchState(WhiteBoardMenuState.getInstance());
            return true;
        }


        return true;
    }

    @Override
    public boolean onLeftClickPressed(int x, int y) {
        if (menu.isIn(x, y)) {
            if (mainController.isKeyPressed(" ")) {
                moving = true;
                mouseX = x - menu.getX();
                mouseY = y - menu.getY();
            }

            if (menu.isInSubmenu(x, y)) {
                subState.onLeftClickPressed(x, y);
                return true;
            }

            buttonId = menu.getButtonId(x, y);

            if (buttonId != -1) {
                menu.pushButton(buttonId);
            }

            int numberSubMenus = menu.getSubmenuNumber();

            if (buttonId < numberSubMenus) {
                menu.switchSubmenu(buttonId);

                switchState();

                subState.onSwitch();
            }

            else if (buttonId - numberSubMenus == 0) {
                System.out.println("Click on apply");

                HashSet<Shape> newShape = new HashSet<>(view.getWhiteBoard().getSelectedShapes());

                model.getWhiteBoard().replace(view.getWhiteBoard().getSelectedShapes(), shapeSave);
                model.execute(new Replace(model.getWhiteBoard(), shapeSave, newShape));

                makeSave();
            }

            else if (buttonId - numberSubMenus == 1) {
                System.out.println("Click on Reset");
                reset();
                onSwitch();
            }

            else if (buttonId - numberSubMenus == 2) {
                System.out.println("Click on Cancel");
                reset();
                view.getWhiteBoard().closeEditionMenu();
                mainController.switchState(WhiteBoardMenuState.getInstance());
                return true;
            }
        } else {
            reset();
            view.getWhiteBoard().closeEditionMenu();
            mainController.switchState(WhiteBoardMenuState.getInstance());
        }

        return true;
    }

    @Override
    public void onSwitch() {
        menu = view.getWhiteBoard().getEditionMenu();
        switchState();
        makeSave();
        subState.onSwitch();
    }

    private void switchState() {
        if (menu.getSelectedMenu().getName().equals("color")) {
            subState = SubMenuColorState.getInstance();
        }

        else if (menu.getSelectedMenu().getName().equals("resize")) {
            if (menu.getSelectedMenu() instanceof SubMenuResizeRectangle)
                subState = SubMenuResizeRectangleState.getInstance();

            else if (menu.getSelectedMenu() instanceof SubMenuResizeGlobal)
                subState = SubMenuResizeGlobalState.getInstance();
        }

        else if (menu.getSelectedMenu().getName().equals("rotate")) {
            subState = SubMenuRotationState.getInstance();
        }

        else if (menu.getSelectedMenu().getName().equals("numberSide")) {
            subState = SubMenuPolygonState.getInstance();
        }

        else if (menu.getSelectedMenu().getName().equals("round")) {
            subState = SubMenuRoundState.getInstance();
        }
    }

    private void makeSave() {
        shapeSave.clear();
        shapeSave.addAll(view.getWhiteBoard().getSelectedShapes());
        HashSet<Shape> selectedClone = new HashSet<>();

        for (Shape s : shapeSave) {
            selectedClone.add((Shape) s.clone());
        }

        model.getWhiteBoard().replace(shapeSave, selectedClone);

        view.getWhiteBoard().clearSelection();
        view.getWhiteBoard().addSelection(selectedClone);
    }


    private void reset() {
        model.getWhiteBoard().replace(view.getWhiteBoard().getSelectedShapes(), shapeSave);

        view.getWhiteBoard().clearSelection();
        view.getWhiteBoard().addSelection(shapeSave);
    }

    @Override
    public boolean onMouseMoved(int x, int y) {
        if (moving) {
            view.getWhiteBoard().moveMenuTo(x - mouseX, y - mouseY);
        } else {
            subState.onMouseMoved(x, y);
        }


        return true;
    }

    @Override
    public boolean onKeyPressed(String keyCode, int mouseX, int mouseY) {
        subState.onKeyPressed(keyCode, mouseX, mouseY);
        return true;
    }

    @Override
    public boolean onKeyReleased(String keyCode, int mouseX, int mouseY) {
        subState.onKeyReleased(keyCode, mouseX, mouseY);
        return true;
    }

    @Override
    public String toString() {
        return "edition menu";
    }
}

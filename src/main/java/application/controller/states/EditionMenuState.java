package application.controller.states;

import application.controller.MainController;
import application.controller.states.substates.SubMenuColorState;
import application.controller.states.substates.SubMenuResizeState;
import application.model.Model;
import application.model.command.concreteCommand.Replace;
import application.model.shape.Shape;
import application.view.MainView;
import application.view.menu.EditionMenu;

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

    private EditionMenuState(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;

        shapeSave = new HashSet<>();

        menu = view.getWhiteBoard().getEditionMenu();

        buttonId = -1;

        SubMenuResizeState.setInstance(mainController, model, view);
        SubMenuColorState.setInstance(mainController, model, view);

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

            if (menu.isInSubmenu(x, y)) {
                subState.onLeftClickPressed(x, y);
                return true;
            }

            buttonId = menu.getButtonId(x, y);

            if (buttonId != -1) {
                menu.pushButton(buttonId);
            }

            if (buttonId == 0) {
                menu.switchSubmenu(0);
                subState = SubMenuColorState.getInstance();
            }
            else if (buttonId == 1) {
                menu.switchSubmenu(1);
                subState = SubMenuResizeState.getInstance();
            }
            else if (buttonId == 2) {
                System.out.println("Click on apply");

                HashSet<Shape> newShape = new HashSet<>(view.getWhiteBoard().getSelectedShapes());

                model.getWhiteBoard().replace(view.getWhiteBoard().getSelectedShapes(), shapeSave);
                model.execute(new Replace(model.getWhiteBoard(), shapeSave, newShape));

                makeSave();
            }
            else if (buttonId == 3) {
                System.out.println("Click on Reset");
                reset();
                makeSave();
            }
            else if (buttonId == 4) {
                System.out.println("Click on Cancel");
                reset();
                view.getWhiteBoard().closeEditionMenu();
                mainController.switchState(WhiteBoardMenuState.getInstance());
                return true;
            }
        }

        return true;
    }

    @Override
    public void onSwitch() {
        makeSave();
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
        subState.onMouseMoved(x, y);

        return true;
    }

    @Override
    public String toString() {
        return "edition menu";
    }
}

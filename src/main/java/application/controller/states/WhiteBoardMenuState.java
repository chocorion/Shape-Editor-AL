package application.controller.states;

import application.controller.MainController;
import application.model.Model;
import application.model.command.concreteCommand.AddComposite;
import application.model.command.concreteCommand.ExpandComposite;
import application.model.shape.CompositeShape;
import application.model.shape.Shape;
import application.view.MainView;
import application.view.menu.WhiteBoardMenu;

import java.util.ArrayList;

public class WhiteBoardMenuState extends ControllerStateImp {
    private static WhiteBoardMenuState instance;
    private final MainController mainController;

    private final Model model;
    private final MainView view;

    private WhiteBoardMenuState(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;

        this.model = model;
        this.view = view;
    }

    @Override
    public boolean onLeftClickPressed(int x, int y) {
        return true;
    }

    @Override
    public boolean onLeftClickReleased(int x, int y) {
        WhiteBoardMenu menu = view.getWhiteBoard().getMenu();
        int itemId = menu.getItemId(x, y);

        if (itemId == 0) {
            model.execute(
                    new AddComposite(model.getWhiteBoard(), view.getWhiteBoard().getSelectedShapes())
            );

            closeAndSwitch();

        } else if (itemId == 1) {
            ArrayList<Shape> selection = view.getWhiteBoard().getSelectedShapes();

            if (selection.size() == 1) {
                if (selection.get(0) instanceof CompositeShape) {
                    model.execute(
                            new ExpandComposite(model.getWhiteBoard(), (CompositeShape) selection.get(0))
                    );
                }

                closeAndSwitch();
            }
        } else if (itemId == 2) {
            view.getWhiteBoard().openEditionMenu(x, y);
            mainController.switchState(EditionMenuStateColor.getInstance());
            return true;
        } else {
            closeAndSwitch();
        }

        return true;
    }

    @Override
    public boolean onRightClickPressed(int x, int y) {
        view.getWhiteBoard().openWhiteboardMenu(x, y);
        return true;
    }

    @Override
    public boolean onMouseMoved(int x, int y) {
        WhiteBoardMenu menu = view.getWhiteBoard().getMenu();
        int itemId = menu.getItemId(x, y);

        menu.setCurrentySelected(itemId);

        return true;
    }

    public static WhiteBoardMenuState getInstance() {
        return instance;
    }

    public static void setInstance(MainController mainController, Model model, MainView view) {
        instance = new WhiteBoardMenuState(mainController, model, view);
    }

    private void closeAndSwitch() {
        view.getWhiteBoard().clearSelection();
        view.getWhiteBoard().closeWhiteboardMenu();

        mainController.switchState(DefaultState.getInstance());
    }

    @Override
    public String toString() {
        return "menu";
    }
}

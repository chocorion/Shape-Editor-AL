package application.controller.states;

import application.controller.MainController;
import application.model.Model;
import application.model.shape.Shape;
import application.view.MainView;
import application.view.areas.Layout;

import java.util.ArrayList;
import java.util.Collections;


public class DefaultState extends ControllerStateImp {
    private static DefaultState state;

    MainController mainController;
    Model model;
    MainView view;

    private DefaultState(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;
    }

    public static void setInstance(MainController mainController, Model model, MainView view) {
        state = new DefaultState(mainController, model, view);
    }


    public static DefaultState getInstance() {
        return state;
    }

    @Override
    public boolean onLeftClickPressed(int x, int y) {
        if (Layout.getTopBar().isIn(x, y)) {
            int buttonId = view.getTopBar().getButtonId(x, y);

            if (buttonId == 0) {
                model.undo();
            } else if (buttonId == 1) {
                model.redo();
            }
        }

        else if (Layout.getToolBar().isIn(x, y)) {
            int shapeId = view.getToolBar().getShapeId(x, y);

            if (shapeId != -1) {
                mainController.switchState(ShapeHoldingState.getInstance());
                return false;
            }
        }

        else if (Layout.getWhiteBoard().isIn(x, y)) {
            Shape currentShape = model.getWhiteBoard().getShapeAt(x, y);

            if (currentShape != null && !mainController.isKeyPressed("CONTROL")) {
               mainController.switchState(ShapeHoldingState.getInstance());
            } else {
                mainController.switchState(SelectionState.getInstance());
            }

            return false;
        }

        return true;
    }

    @Override
    public boolean onRightClickPressed(int x, int y) {
        if (Layout.getWhiteBoard().isIn(x, y)) {
            Shape currentShape = model.getWhiteBoard().getShapeAt(x, y);

            if (currentShape != null) {
                view.getWhiteBoard().addSelection(new ArrayList<>(Collections.singletonList(currentShape)));
                mainController.switchState(WhiteBoardMenuState.getInstance());
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean onKeyPressed(String keyCode, int mouseX, int mouseY) {
        if (keyCode.equals("SPACE")) {
            if (Layout.getWhiteBoard().isIn(mouseX, mouseY)) {
                // TODO: What if mouse was already pressed when switching to movingShape ?
                mainController.switchState(MovingShape.getInstance());

                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "default";
    }
}

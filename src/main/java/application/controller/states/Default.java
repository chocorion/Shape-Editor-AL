package application.controller.states;

import application.controller.MainController;
import application.model.Model;
import application.model.shape.Shape;
import application.view.MainView;


public class Default extends ControllerStateImp {
    private static Default state;

    MainController mainController;
    Model model;
    MainView view;

    private Default(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;
    }

    public static void setInstance(MainController mainController, Model model, MainView view) {
        state = new Default(mainController, model, view);
    }


    public static Default getInstance() {
        return state;
    }

    @Override
    public boolean onLeftClickPressed(int x, int y) {
        if (model.getTopBar().isIn(x, y)) {
            int buttonId = view.getTopBar().getButtonId(x, y);

            if (buttonId != -1)
                model.getTopBar().clickOnButton(buttonId);
        }

        else if (model.getToolBar().isIn(x, y)) {
            int shapeId = view.getToolBar().getShapeId(x, y);

            if (shapeId != -1) {
                mainController.switchState(ShapeHolding.getInstance());
                return false;
            }
        }

        else if (model.getWhiteBoard().isIn(x, y)) {
            Shape currentShape = model.getWhiteBoard().getShapeAt(x, y);

            if (currentShape != null) {
               mainController.switchState(ShapeHolding.getInstance());
            } else {
                mainController.switchState(Selection.getInstance());
            }

            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "default";
    }
}

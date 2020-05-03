package application.controller.states;

import application.controller.MainController;
import application.model.Model;
import application.model.command.concreteCommand.AddShape;
import application.model.shape.Shape;
import application.utils.ShapeContainer;
import application.view.MainView;

public class ShapeHoldingState extends ControllerStateImp {
    private static ShapeHoldingState instance;

    private ShapeContainer from;
    private Shape holdedShape;

    private MainController mainController;
    private Model model;
    private MainView view;

    private ShapeHoldingState(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;
    }

    public static void setInstance(MainController mainController, Model model, MainView view) {
        instance = new ShapeHoldingState(mainController, model, view);
    }

    public static ShapeHoldingState getInstance() {
        return instance;
    }

    @Override
    public boolean onLeftClickPressed(int x, int y) {
        if (model.getToolBar().isIn(x, y)) {
            int shapeId = view.getToolBar().getShapeId(x, y);

            if (shapeId != -1) {
                holdedShape = model.getToolBar().getShape(shapeId);
                from = model.getToolBar();
            } else {
                mainController.switchState(DefaultState.getInstance());
            }
        }

        else if (model.getWhiteBoard().isIn(x, y)) {
            Shape s = model.getWhiteBoard().getShapeAt(x, y);

            if (s == null) {
                mainController.switchState(DefaultState.getInstance());
            } else {
                holdedShape = s;
                from = model.getWhiteBoard();
            }
        }

        return true;
    }

    @Override
    public boolean onLeftClickReleased(int x, int y) {
        // Ici, penser à la gestion de la corbeille
        ShapeContainer dest = null;

        if (model.getWhiteBoard() != from && model.getWhiteBoard().isIn(x, y)) {
            dest = model.getWhiteBoard();
        } else if (model.getToolBar() != from && model.getToolBar().isIn(x, y)) {
            dest = model.getToolBar();
        }

        if (dest != null) {
            Shape clonedShape = (Shape) holdedShape.clone();
            clonedShape.moveTo(x, y);

            model.execute(new AddShape(dest, clonedShape));
        }

        mainController.switchState(DefaultState.getInstance());

        return true;
    }

    @Override
    public String toString() {
        return "shapeHolding";
    }
}

package application.controller.areas;

import application.controller.MainController;
import application.model.areas.WhiteBoard;
import application.model.shape.Shape;
import application.view.areas.WhiteBoardView;

public class WhiteBoardController {
    private WhiteBoard model;
    private WhiteBoardView view;
    private MainController mainController;

    private int selectionStartX, selectionStartY;
    private int selectionEndX, selectionEndY;

    public WhiteBoardController(MainController mainController, WhiteBoard model, WhiteBoardView view) {
        this.model = model;
        this.view = view;

        this.mainController = mainController;
    }

    public void onLeftClickPressed(int x, int y) {
        System.out.println("Click on the whiteboard");

        if (mainController.isSelectionSet() && !(mainController.isMenuOpen() && view.clickOnGroup(x , y))){
            mainController.setSelection(false);

            view.undrawSelect(
                    Math.min(selectionEndX, selectionStartX),
                    Math.min(selectionEndY, selectionStartY),
                    Math.abs(selectionStartX - selectionEndX),
                    Math.abs(selectionStartY - selectionEndY)
            );
            model.update();
        }

        if (!mainController.isSelectionSet()) {
            selectionStartX = x;
            selectionStartY = y;
        }

        Shape currentShape = model.getShapeAt(x, y);
        if (currentShape != null) {
            mainController.setHoldedShape(currentShape, model);
        }
    }
}

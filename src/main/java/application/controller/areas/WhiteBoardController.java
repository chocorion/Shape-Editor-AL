package application.controller.areas;

import application.controller.MainController;
import application.model.areas.WhiteBoard;
import application.model.command.concreteCommand.AddComposite;
import application.model.command.concreteCommand.AddShape;
import application.model.shape.Shape;
import application.view.areas.WhiteBoardView;

public class WhiteBoardController {
    private WhiteBoard model;
    private WhiteBoardView view;
    private MainController mainController;

    private int selectionStartX, selectionStartY;
    private int selectionEndX, selectionEndY;

    private boolean leftClick;

    public WhiteBoardController(MainController mainController, WhiteBoard model, WhiteBoardView view) {
        this.model = model;
        this.view = view;

        this.mainController = mainController;

        leftClick = false;
    }

    public void onLeftClickPressed(int x, int y) {
        leftClick = true;

        if (mainController.isSelectionSet() && !(view.isMenuOpen() && view.isInMenu(x , y))){
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

    public void onLeftClickReleased(int x, int y) {
        System.out.println("In whiteboard :");
        if (view.isMenuOpen()) {
            System.out.println("Menu is open. In menu ? " + view.isInMenu(x, y));
            if(view.isInMenu(x , y) && mainController.isSelectionSet()) {
                clearSelection();
                System.out.println("Ask to close menu");
                view.closeWhiteboardMenu();

                // Only group command for the moment
                mainController.addCommand(
                        new AddComposite(model, selectionStartX, selectionStartY, selectionEndX, selectionEndY)
                );
                this.model.update();
            }

            else {
                view.closeWhiteboardMenu();
            }

            mainController.setMenu(false);
            mainController.setSelection(false);
        }

        else if (leftClick && mainController.isDraggeg()) {
            closeSelection(x, y);
            mainController.setSelection(true);
        }

        else if (mainController.isHoldingShape()) {
            Shape clone = mainController.getHoldedShapeClone();
            clone.moveTo(x, y);

            mainController.addCommand(new AddShape(model, clone));
        }

        leftClick = false;
    }

    public void onRightClickPressed(int x, int y) {
        view.openWhiteboardMenu(x, y);
        mainController.setMenu(true);
    }

    private void closeSelection(int x, int y) {
        selectionEndX = x;
        selectionEndY = y;

        view.drawSelection(
            Math.min(selectionEndX, selectionStartX),
            Math.min(selectionEndY, selectionStartY),
            Math.abs(selectionStartX - selectionEndX),
            Math.abs(selectionStartY - selectionEndY)
        );
    }

    private void clearSelection() {
        view.undrawSelect(
            Math.min(selectionEndX, selectionStartX),
            Math.min(selectionEndY, selectionStartY),
            Math.abs(selectionStartX - selectionEndX),
            Math.abs(selectionStartY - selectionEndY)
        );
    }
}

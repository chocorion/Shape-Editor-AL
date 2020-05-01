package application.controller.areas;

import application.controller.MainController;
import application.model.areas.WhiteBoard;
import application.model.command.concreteCommand.AddComposite;
import application.model.command.concreteCommand.AddShape;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.view.areas.WhiteBoardView;

import java.util.ArrayList;

public class WhiteBoardController {
    private WhiteBoard model;
    private WhiteBoardView view;
    private MainController mainController;

    private int selectionStartX, selectionStartY;
    private int selectionEndX, selectionEndY;

    private boolean leftClick;
    private boolean selection;
    private boolean canDrawSelection;

    private ArrayList<Shape> selectedShapes;

    public WhiteBoardController(MainController mainController, WhiteBoard model, WhiteBoardView view) {
        this.model = model;
        this.view = view;

        this.mainController = mainController;

        leftClick     = false;
        selection     = false;
        canDrawSelection = false;

        selectedShapes = new ArrayList<>();
    }

    public void onLeftClickPressed(int x, int y) {
        leftClick = true;

        if (mainController.isSelectionSet() && !(view.isMenuOpen() && view.isInMenu(x , y))){
            System.out.println("HERE CLEARING");
            mainController.setSelection(false);
            view.clearSelection();
            model.update();
        }

        if (!mainController.isSelectionSet()) {
            selectionStartX = x;
            selectionStartY = y;
        }

        Shape currentShape = model.getShapeAt(x, y);
        if (currentShape != null) {
            mainController.setHoldedShape(currentShape, model);
            canDrawSelection = false;
        } else {
            canDrawSelection = true;
        }
    }

    public void onLeftClickReleased(int x, int y) {
        if (view.isMenuOpen()) {
            if(view.isInMenu(x , y) && mainController.isSelectionSet()) {

                // Only group command for the moment
                clearSelection();
                view.closeWhiteboardMenu();
                view.clearSelection();

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

        else if (leftClick && selection) {
            selection = false;
            clearSelection();
            mainController.setSelection(true);
            model.update();

            Rectangle selection = new Rectangle(
                    selectionStartX,
                    selectionStartY,
                    selectionEndX - selectionStartX,
                    selectionEndY - selectionStartY
            );

            selectedShapes.clear();
            for (Shape shape : model.getInnerShapes()) {
                if (shape.intersect(selection)) {
                    selectedShapes.add(shape);
                }
            }

            view.addSelection(selectedShapes);
            view.update();
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

    public void onMouseDragged(int x, int y) {
        // User is creating selection
        if (leftClick && canDrawSelection) {
            clearSelection();
            closeSelection(x, y);
        }
    }

    private void closeSelection(int x, int y) {
        selection = true;

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
        if (!selection) return;


        view.undrawSelect(
            Math.min(selectionEndX, selectionStartX),
            Math.min(selectionEndY, selectionStartY),
            Math.abs(selectionStartX - selectionEndX),
            Math.abs(selectionStartY - selectionEndY)
        );

        // Ugly, have to find another way of doing it.
        model.update();

        selection = false;
    }
}

package application.controller.states;

import application.controller.MainController;
import application.model.Model;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.view.MainView;

import java.util.ArrayList;

public class Selection extends ControllerStateImp {
    private final MainController mainController;
    private static Selection instance;

    private final MainView view;
    private final Model model;

    private int startX;
    private int startY;

    private int endX;
    private int endY;

    private boolean selectionDone;
    private boolean doingSelection;

    private final ArrayList<Shape> selectedShape;

    private Selection(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;

        this.model = model;
        this.view = view;

        selectionDone  = false;
        doingSelection = false;

        selectedShape = new ArrayList<>();
    }

    public static void setInstance(MainController mainController, Model model, MainView view) {
        instance = new Selection(mainController, model, view);
    }

    public static Selection getInstance() {
        return instance;
    }

    @Override
    public boolean onLeftClickPressed(int x, int y) {
        if (selectionDone) {
            selectionDone = false;
            selectedShape.clear();

            view.getWhiteBoard().clearSelection();
            view.getWhiteBoard().update();

            mainController.switchState(Default.getInstance());

            return true;
        }

        doingSelection = true;

        startX = endX = x;
        startY = endY = y;

        return true;
    }

    @Override
    public boolean onLeftClickReleased(int x, int y) {
        System.out.println("In selection :");

        if (startY == endY && startX == endX) {
            System.out.println("Empty selection");
            mainController.switchState(Default.getInstance());
            return true;
        }

        view.getWhiteBoard().undrawSelect(
                Math.min(endX, startX),
                Math.min(endY, startY),
                Math.abs(startX - endX),
                Math.abs(startY - endY)
        );
        view.getWhiteBoard().update();

        if (!model.getWhiteBoard().isIn(x, y)) {
            mainController.switchState(Default.getInstance());
        }

        else {

            doingSelection = false;


            Rectangle selection = new Rectangle(
                    Math.min(endX, startX),
                    Math.min(endY, startY),
                    Math.abs(startX - endX),
                    Math.abs(startY - endY)
            );

            for (Shape shape : model.getWhiteBoard().getInnerShapes()) {
                if (shape.intersect(selection)) {
                    selectedShape.add(shape);
                }
            }

            if (selectedShape.size() > 0) {
                selectionDone  = true;

                view.getWhiteBoard().addSelection(selectedShape);
                view.getWhiteBoard().update();
            } else {
                mainController.switchState(Default.getInstance());
            }


        }

        return true;
    }

    @Override
    public boolean onRightClickPressed(int x, int y) {
        if (selectionDone) {
            mainController.switchState(Menu.getInstance());
            return false;
        }

        return true;
    }

    @Override
    public boolean onMouseDragged(int x, int y) {
        if (doingSelection && model.getWhiteBoard().isIn(x, y)) {
            view.getWhiteBoard().undrawSelect(
                    Math.min(endX, startX),
                    Math.min(endY, startY),
                    Math.abs(startX - endX),
                    Math.abs(startY - endY)
            );

            view.getWhiteBoard().update();
            endX = x;
            endY = y;

            view.getWhiteBoard().drawSelection(
                    Math.min(endX, startX),
                    Math.min(endY, startY),
                    Math.abs(startX - endX),
                    Math.abs(startY - endY)
            );
        }

        return true;
    }

    @Override
    public String toString() {
        return "selection";
    }
}

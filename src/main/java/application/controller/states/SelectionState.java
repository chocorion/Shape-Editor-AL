package application.controller.states;

import application.controller.MainController;
import application.model.Model;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.view.MainView;
import application.view.areas.Layout;

import java.util.ArrayList;
import java.util.Collections;

public class SelectionState extends ControllerStateImp {
    private final MainController mainController;
    private static SelectionState instance;

    private final MainView view;
    private final Model model;

    private int startX;
    private int startY;

    private int endX;
    private int endY;

    private boolean selectionDone;
    private boolean doingSelection;

    private boolean ctrlActivate;

    private final ArrayList<Shape> selectedShape;

    private SelectionState(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;

        this.model = model;
        this.view = view;

        selectionDone  = false;
        doingSelection = false;
        ctrlActivate = false;

        selectedShape = new ArrayList<>();
    }

    public static void setInstance(MainController mainController, Model model, MainView view) {
        instance = new SelectionState(mainController, model, view);
    }

    public static SelectionState getInstance() {
        return instance;
    }

    @Override
    public boolean onLeftClickPressed(int x, int y) {
        if (!ctrlActivate && mainController.isKeyPressed("CONTROL")) {
            ctrlActivate = true;
        }

        if (selectionDone && !ctrlActivate) {
            selectionDone = false;
            selectedShape.clear();

            view.getWhiteBoard().clearSelection();
            view.getWhiteBoard().update();

            mainController.switchState(DefaultState.getInstance());

            return true;
        }

        doingSelection = true;

        startX = endX = x;
        startY = endY = y;

        return true;
    }

    @Override
    public boolean onLeftClickReleased(int x, int y) {
        if (startY == endY && startX == endX) {
            if (!ctrlActivate && mainController.isKeyPressed("CONTROL")) {
                ctrlActivate = true;
            }

            if (!ctrlActivate)
                mainController.switchState(DefaultState.getInstance());
            else {
                if (Layout.getWhiteBoard().isIn(x, y)) {
                    Shape s = model.getWhiteBoard().getShapeAt(x, y);

                    if (s != null) {
                        view.getWhiteBoard().addSelection(new ArrayList<>(Collections.singleton(s)));
                        view.getWhiteBoard().update();
                    }
                }
            }
            return true;
        }

        view.getWhiteBoard().clearSelection();
        view.getWhiteBoard().update();

        if (!Layout.getWhiteBoard().isIn(x, y)) {
            if (!ctrlActivate)
                mainController.switchState(DefaultState.getInstance());
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
                mainController.switchState(DefaultState.getInstance());
            }


        }

        return true;
    }

    @Override
    public boolean onRightClickPressed(int x, int y) {
        if (selectionDone) {
            mainController.switchState(WhiteBoardMenuState.getInstance());
            return false;
        }

        return true;
    }

    @Override
    public boolean onMouseMoved(int x, int y) {
        if (doingSelection && Layout.getWhiteBoard().isIn(x, y)) {
            view.getWhiteBoard().update();
            endX = x;
            endY = y;

            view.getViewBridge().drawSelection(
                    Math.min(endX, startX),
                    Math.min(endY, startY),
                    Math.abs(startX - endX),
                    Math.abs(startY - endY)
            );
        }

        return true;
    }

    @Override
    public boolean onKeyPressed(String keyCode, int mouseX, int mouseY) {
        if (keyCode.equals("CONTROL")) {
            ctrlActivate = true;
        }

        return true;
    }

    @Override
    public boolean onKeyReleased(String keyCode, int mouseX, int mouseY) {
        if (keyCode.equals("CONTROL")) {
            ctrlActivate = false;

            if (doingSelection) {
                selectionDone = true;
                mainController.switchState(DefaultState.getInstance());
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "selection";
    }
}

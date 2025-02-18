package application.controller.states;

import application.controller.MainController;
import application.model.Model;
import application.model.shape.Shape;
import application.view.MainView;
import application.view.areas.Layout;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represent the default state of the mainController, when no
 * specific action is taken.
 */
public class DefaultState extends ControllerStateImp {
    private static DefaultState state;

    private final MainController mainController;
    private final Model model;
    private final MainView view;
    private boolean leftClick;


    /**
     * Parameterized constructor.
     * @param mainController The current MainController.
     * @param model The current model of the application.
     * @param view The MainvVew of the application
     */
    private DefaultState(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;
    }


    /**
     * Set the instance of defaultState.
     * @param mainController The current MainController.
     * @param model The current model of the application.
     * @param view The MainView of the application
     */
    public static void setInstance(MainController mainController, Model model, MainView view) {
        state = new DefaultState(mainController, model, view);
    }


    /**
     * Return the current instance.
     * @return Current instance.
     */
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
            }else if (buttonId == 2){
                view.save();
            }else if (buttonId ==3){
                view.load();
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

            if (mainController.isKeyPressed(" ")) {
                mainController.switchState(MovingShape.getInstance());
                return false;
            }


            if (currentShape != null && mainController.isKeyPressed("ALT"))
                model.getWhiteBoard().toFirstPlan(currentShape);

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
        if (mainController.isKeyPressed("CONTROL")) {
            if (keyCode.equals("\u001A")) {
                model.undo();
            } else if (keyCode.equals("\u0019")) {
                model.redo();
            }
        }


        return true;
    }

    @Override
    public String toString() {
        return "default";
    }
}

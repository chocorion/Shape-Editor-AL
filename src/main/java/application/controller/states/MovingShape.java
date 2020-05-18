package application.controller.states;

import application.controller.MainController;
import application.model.Model;
import application.model.command.concreteCommand.MoveShape;
import application.model.shape.Shape;
import application.view.MainView;
import application.view.areas.Layout;

/**
 * Represent the mainController state when a shape is moved
 * on the whiteboard by the user.
 */
public class MovingShape extends ControllerStateImp {
    private static MovingShape state;

    MainController mainController;
    Model model;
    MainView view;

    Shape holding;
    int diffX, diffY;
    double oldX, oldY;

    /**
     * Parameterized constructor.
     * @param mainController The current mainController.
     * @param model The current model of the application.
     * @param view the current mainView of the application.
     */
    private MovingShape(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;
    }


    /**
     * Set the instance of MovingShape.
     * @param mainController The current mainController.
     * @param model The current model of the application.
     * @param view the current mainView of the application.
     */
    public static void setInstance(MainController mainController, Model model, MainView view) {
        state = new MovingShape(mainController, model, view);
    }


    @Override
    public boolean onLeftClickPressed(int x, int y) {
        if (Layout.getWhiteBoard().isIn(x, y)) {
            holding = model.getWhiteBoard().getShapeAt(x, y);

            if (holding != null) {
                oldX = holding.getMinX();
                oldY = holding.getMinY();

                diffX = (int) (x - holding.getMinX());
                diffY = (int) (y - holding.getMinY());
            }
        }

        return true;
    }


    @Override
    public boolean onLeftClickReleased(int x, int y) {
        releaseShape(x, y);
        mainController.switchState(DefaultState.getInstance());

        return false;
    }


    @Override
    public boolean onMouseMoved(int x, int y) {
        if (holding != null) {
            if (Layout.getWhiteBoard().isIn(x - diffX, y - diffY)) {
                model.getWhiteBoard().moveShape(holding, x - diffX, y - diffY);
            }
        }

        return true;
    }


    @Override
    public boolean onKeyReleased(String keyCode, int mouseX, int mouseY) {
        if (keyCode.equals(" ")) {
            releaseShape(mouseX, mouseY);
            mainController.switchState(DefaultState.getInstance());

            return false;
        }

        return true;
    }


    /**
     * Add command to put the shape to its position in the whiteboard.
     * @param x X coords of the mouse.
     * @param y Y coords of the mouse.
     */
    private void releaseShape(int x, int y) {
        if (holding == null) return;

        double newX = holding.getMinX();
        double newY = holding.getMinY();

        holding.moveTo(oldX, oldY);
        model.execute(new MoveShape(model.getWhiteBoard(), holding, x - diffX, y - diffY));

        holding = null;
    }


    /**
     * Return the current instance.
     * @return Current instance.
     */
    public static MovingShape getInstance() {
        return state;
    }

    @Override
    public String toString() {
        return "moving shape";
    }
}

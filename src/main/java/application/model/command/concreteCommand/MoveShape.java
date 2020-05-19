package application.model.command.concreteCommand;

import application.model.areas.WhiteBoard;
import application.model.command.Command;
import application.model.shape.Shape;


/**
 * Represent a command that move shape in a whiteboard.
 */
public class MoveShape implements Command {
    WhiteBoard whiteBoard;
    private Shape shape;
    double newX, newY, oldX, oldY;


    /**
     * Parameterized constructor.
     * @param whiteBoard The current whiteboard to move shape in.
     * @param shape The shape to move.
     * @param newX The new x coords of the shape top left position.
     * @param newY The new y coords of the shape top left position.
     */
    public MoveShape(WhiteBoard whiteBoard, Shape shape, double newX, double newY) {
        this.whiteBoard = whiteBoard;

        this.shape = shape;
        this.newX = newX;
        this.newY = newY;

        oldX = shape.getMinX();
        oldY = shape.getMinY();
    }


    @Override
    public void execute() {
        whiteBoard.moveShape(shape, newX, newY);
    }


    @Override
    public void inverse() {
        whiteBoard.moveShape(shape, oldX, oldY);
    }
}

package application.model.command.concreteCommand;

import application.model.areas.ShapeContainer;
import application.model.areas.WhiteBoard;
import application.model.command.Command;
import application.model.shape.Shape;

import java.util.HashSet;
import java.util.Set;

public class MoveShape implements Command {
    WhiteBoard whiteBoard;
    private Shape shape;
    double newX, newY, oldX, oldY;

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

package application.model.command.concreteCommand;

import application.model.areas.ShapeContainer;
import application.model.command.Command;
import application.model.shape.Shape;

/**
 * Represent a command that remove a shape from a container.
 */
public class RemoveShape implements Command {
    Shape shape;
    ShapeContainer container;

    /**
     * Parameterized constructor.
     * @param shape The shape to remove.
     * @param container The container of the shape.
     */
    public RemoveShape(Shape shape, ShapeContainer container) {
        this.shape = shape;
        this.container = container;
    }


    @Override
    public void execute() {
        container.removeShape(shape);
    }


    @Override
    public void inverse() {
        container.addShape(shape);
    }
}

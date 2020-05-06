package application.model.command.concreteCommand;

import application.model.areas.ShapeContainer;
import application.model.command.Command;
import application.model.shape.Shape;

public class RemoveShape implements Command {
    Shape shape;
    ShapeContainer container;

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

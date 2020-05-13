package application.model.command.concreteCommand;

import application.model.areas.ShapeContainer;
import application.model.command.Command;
import application.model.shape.Shape;

import java.util.Set;

public class Replace implements Command {
    private Set<Shape> oldShape, newShape;
    private ShapeContainer container;

    public Replace(ShapeContainer container, Set<Shape> oldShape, Set<Shape> newShape) {
        this.oldShape = oldShape;
        this.newShape = newShape;
    }

    @Override
    public void execute() {
        for (Shape shape : oldShape) {
            container.removeShape(shape);
        }

        for (Shape shape : newShape) {
            container.addShape(shape);
        }
    }

    @Override
    public void inverse() {
        for (Shape shape : newShape) {
            container.removeShape(shape);
        }

        for (Shape shape : oldShape) {
            container.addShape(shape);
        }
    }
}

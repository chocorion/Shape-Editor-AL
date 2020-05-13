package application.model.command.concreteCommand;

import application.model.areas.ShapeContainer;
import application.model.command.Command;
import application.model.shape.Shape;

import java.util.HashSet;
import java.util.Set;

public class Replace implements Command {
    private Set<Shape> oldShape, newShape;
    private ShapeContainer container;

    public Replace(ShapeContainer container, Set<Shape> oldShape, Set<Shape> newShape) {
        this.container = container;

        this.oldShape = new HashSet<>(oldShape);
        this.newShape = new HashSet<>(newShape);
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
        System.out.println("Reversing replace cmd");

        for (Shape shape : newShape) {
            container.removeShape(shape);
        }

        for (Shape shape : oldShape) {
            container.addShape(shape);
        }
    }
}

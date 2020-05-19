package application.model.command.concreteCommand;

import application.model.areas.ShapeContainer;
import application.model.command.Command;
import application.model.shape.Shape;

import java.util.HashSet;
import java.util.Set;

/**
 * Represent a command that replace a set of shape by
 * another one in a container.
 */
public class Replace implements Command {
    private final Set<Shape> oldShape;
    private final Set<Shape> newShape;
    private final ShapeContainer container;


    /**
     * Parameterized constructor.
     * @param container The container of the shapes.
     * @param oldShape Set of shapes to replace.
     * @param newShape Set of new shape to put in the container.
     */
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
        for (Shape shape : newShape) {
            container.removeShape(shape);
        }

        for (Shape shape : oldShape) {
            container.addShape(shape);
        }
    }
}

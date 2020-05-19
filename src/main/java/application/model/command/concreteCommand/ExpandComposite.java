package application.model.command.concreteCommand;

import application.model.command.Command;
import application.model.shape.CompositeShape;
import application.model.shape.Shape;
import application.model.areas.ShapeContainer;

/**
 * Represent a command that replace a composite by it's inner shapes in a container.
 */
public class ExpandComposite implements Command {
    private final ShapeContainer container;
    private final CompositeShape shape;


    /**
     * Parameterized constructor.
     * @param container The container of the composite.
     * @param composite The composite to expand.
     */
    public ExpandComposite(ShapeContainer container, CompositeShape composite) {
        this.container = container;
        this.shape = composite;
    }


    @Override
    public void execute() {
        container.removeShape(shape);

        for (Shape s : shape.getShape()) {
            container.addShape(s);
        }
    }


    @Override
    public void inverse() {
        for (Shape s : shape.getShape()) {
            container.removeShape(s);
        }

        container.addShape(shape);
    }

    
    @Override
    public String toString() {
        return "expandComposite(" + container + ", " + shape +")";
    }
}

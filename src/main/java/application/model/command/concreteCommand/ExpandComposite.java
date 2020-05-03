package application.model.command.concreteCommand;

import application.model.command.Command;
import application.model.shape.CompositeShape;
import application.model.shape.Shape;
import application.utils.ShapeContainer;

public class ExpandComposite implements Command {
    private ShapeContainer container;
    private CompositeShape shape;

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
}

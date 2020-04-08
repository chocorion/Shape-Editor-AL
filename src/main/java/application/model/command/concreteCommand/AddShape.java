package application.model.command.concreteCommand;

import application.model.command.Command;
import application.model.shape.Shape;
import application.utils.ShapeContainer;

public class AddShape implements Command {
    private ShapeContainer shapeContainer;
    private Shape shape;

    public AddShape(ShapeContainer shapeContainer, Shape shape) {
        this.shapeContainer = shapeContainer;
        this.shape = shape;
    }

    @Override
    public void execute() {
        this.shapeContainer.addShape(this.shape);
    }

    @Override
    public void inverse() {
        this.shapeContainer.removeShape(this.shape);
    }
}

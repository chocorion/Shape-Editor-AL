package application.model.command.concreteCommand;

import application.model.command.Command;
import application.model.shape.Shape;
import application.model.areas.ShapeContainer;

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
        shapeContainer.removeShape(shape);
    }

    @Override
    public String toString() {
        return "addShape(" + this.shapeContainer + ", " + this.shape +")";
    }
}

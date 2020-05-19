package application.model.command.concreteCommand;

import application.model.command.Command;
import application.model.shape.Shape;
import application.model.areas.ShapeContainer;


/**
 * Represent a command that add shape in a container.
 */
public class AddShape implements Command {
    private ShapeContainer shapeContainer;
    private Shape shape;


    /**
     * Parameterized constructor/
     * @param shapeContainer Container to add shape in.
     * @param shape Shape to add in container.
     */
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

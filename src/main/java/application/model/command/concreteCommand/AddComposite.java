package application.model.command.concreteCommand;

import application.model.command.Command;
import application.model.shape.CompositeShape;
import application.model.shape.Shape;
import application.model.areas.ShapeContainer;

import java.util.ArrayList;

public class AddComposite implements Command {
    private final ShapeContainer shapeContainer;
    private final CompositeShape shape;


    public AddComposite(ShapeContainer shapeContainer, ArrayList<Shape> shapes) {
        this.shape = new CompositeShape();
        this.shapeContainer = shapeContainer;

        for (Shape s : shapes) {
            shape.add(s);
        }
    }

    @Override
    public void execute() {
        this.shapeContainer.addShape(this.shape);

        for (Shape shape : this.shape.getShape()) {
            shapeContainer.removeShape(shape);
        }

    }

    @Override
    public void inverse() {
        this.shapeContainer.removeShape(this.shape);

        for (Shape shape : this.shape.getShape()) {
            shapeContainer.addShape(shape);
        }
    }

    @Override
    public String toString() {
        return "addComposite(" + this.shapeContainer + ", " + this.shape +")";
    }
}

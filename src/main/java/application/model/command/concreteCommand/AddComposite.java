package application.model.command.concreteCommand;

import application.model.command.Command;
import application.model.shape.CompositeShape;
import application.model.shape.Shape;
import application.model.areas.ShapeContainer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;


/**
 * Represent a command that create a composite in a shape container.
 */
public class AddComposite implements Command {
    private final ShapeContainer shapeContainer;
    private final CompositeShape shape;


    /**
     * Parameterized constructor.
     * @param shapeContainer The shape container to add composite in.
     * @param shapes Shapes to create composite from.
     */
    public AddComposite(ShapeContainer shapeContainer, Set<Shape> shapes) {
        this.shape = new CompositeShape();
        this.shapeContainer = shapeContainer;

        ArrayList<Shape> shapeList = new ArrayList<>(shapes);
        shapeList.sort(new Comparator<Shape>() {
            @Override
            public int compare(Shape shape1, Shape shape2) {
                return (shapeContainer.getShapePlace(shape1) > shapeContainer.getShapePlace(shape2)) ? 1 : -1;
            }
        });
        
        for (Shape s : shapeList) {
            System.out.println("Adding shape with priority " + shapeContainer.getShapePlace(s));
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

package application.model.command.concreteCommand;

import application.model.areas.WhiteBoard;
import application.model.command.Command;
import application.model.shape.CompositeShape;
import application.model.shape.Shape;
import application.utils.ShapeContainer;

import java.util.ArrayList;

public class AddComposite implements Command {
    private ShapeContainer shapeContainer;
    private CompositeShape shape;

    public AddComposite(WhiteBoard shapeContainer,int beginX, int beginY,int endX, int endY) {
        this.shapeContainer = shapeContainer;
        this.shape = new CompositeShape();

        System.out.println("From " + beginX + ", " + beginY + "to " + endX + ", " + endY);
        for(int j =beginY; j < beginY+endY; j++) {
            for (int i = beginX; i < beginX + endX; i++) {
                Shape s = shapeContainer.getShapeAt(i,j);
                if (s != null && !this.shape.isIn(s.getMinX(), s.getMinY())){
                    System.out.println("ADDING SHAPE");
                    this.shape.add(s);
                }
            }
        }
    }

    public AddComposite(ShapeContainer shapeContainer, ArrayList<Shape> shapes) {
        this.shape = new CompositeShape();
        this.shapeContainer = shapeContainer;

        for (Shape s : shapes) {
            shape.add(s);
        }
    }

    @Override
    public void execute() {
        System.out.println("ADDING CONTAINER");
        this.shapeContainer.addShape(this.shape);
        for (Shape shape : this.shape.getShape()) {
            System.out.println("Removing shape " + shape);
            shapeContainer.removeShape(shape);
        }

    }

    @Override
    public void inverse() {
        System.out.println("Removing shape : " + this.shape);
        this.shapeContainer.removeShape(this.shape);
        for (Shape shape : this.shape.getShape()) {
            shapeContainer.addShape(shape);
        }
    }

    @Override
    public String toString() {
        return "Command(" + this.shapeContainer + ", " + this.shape +")";
    }
}

package application.model.command.concreteCommand;

import application.model.areas.WhiteBoard;
import application.model.command.Command;
import application.model.shape.CompositeShape;
import application.model.shape.Shape;
import application.utils.ShapeContainer;

public class AddComposite implements Command {
    private WhiteBoard shapeContainer;
    private CompositeShape shape;

    public AddComposite(WhiteBoard shapeContainer,int beginX, int beginY,int endX, int endY) {
        this.shapeContainer = shapeContainer;
        this.shape = new CompositeShape();
        for(int j =beginY; j < beginY+endY; j++) {
            for (int i = beginX; i < beginX + endX; i++) {
                Shape s = shapeContainer.getShapeAt(i,j);
                if (s!= null && this.shape.isIn(s.getMinX(), s.getMinY())) this.shape.add(s);
            }
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

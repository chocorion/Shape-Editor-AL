package application.model.areas;

import application.model.shape.CompositeShape;
import application.model.shape.Shape;
import application.utils.ModelObservableImp;

import java.util.ArrayList;

public class WhiteBoard extends ModelObservableImp {
    private ArrayList<Shape> shapes;

    public WhiteBoard() {
        this.shapes = new ArrayList<>();
    }

    public void update() {
        super.notifyObserver();
    }


    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    public ArrayList<Shape> getInnerShapes() {
        return new ArrayList<>(this.shapes);
    }
}

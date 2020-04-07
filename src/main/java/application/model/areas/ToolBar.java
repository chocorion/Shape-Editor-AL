package application.model.areas;

import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.ModelObservableImp;

import java.util.ArrayList;

public class ToolBar extends ModelObservableImp {
    private ArrayList<Shape> shapes;

    public ToolBar() {
        this.shapes = new ArrayList<>();
        this.shapes.add(new Rectangle(0, 0, 40, 30));
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

package application.model.areas;

import application.model.Model;
import application.model.shape.Shape;
import application.utils.ModelObservableImp;
import application.view.areas.Layout;

import java.util.ArrayList;

public class WhiteBoard extends ModelObservableImp implements ShapeContainer {
    private final ArrayList<Shape> shapes;
    private Model model;

    public WhiteBoard(Model model) {
        shapes = new ArrayList<>();
        this.model = model;
    }

    public void update() {
        super.notifyObserver();
    }

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
        update();
    }

    @Override
    public void removeShape(Shape shape) {
        shapes.remove(shape);
        update();
    }

    public ArrayList<Shape> getInnerShapes() {
        return new ArrayList<>(shapes);
    }

    public Shape getShapeAt(int x, int y) {
        for (Shape shape : shapes) {
            if (shape.isIn(x, y)) {
                return shape;
            }
        }

        return null;
    }

    public void moveShape(Shape shape, int x, int y) {
        shape.moveTo(x, y);
        update();
    }
}

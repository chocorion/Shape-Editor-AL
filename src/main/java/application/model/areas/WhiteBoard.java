package application.model.areas;

import application.model.Model;
import application.model.shape.CompositeShape;
import application.model.shape.Shape;
import application.utils.ModelObservableImp;
import application.utils.ShapeContainer;

import java.util.ArrayList;

public class WhiteBoard extends ModelObservableImp implements ShapeContainer {
    private ArrayList<Shape> shapes;
    private Model model;

    public WhiteBoard(Model model) {
        this.shapes = new ArrayList<>();
        this.model = model;
    }

    public void update() {
        super.notifyObserver();
    }

    @Override
    public void addShape(Shape shape) {
        this.shapes.add(shape);
        this.update();
    }

    @Override
    public void removeShape(Shape shape) {
        System.out.println("REMOVING SHAPE FROM WHITEBOARD");
        this.shapes.remove(shape);
        this.update();
    }

    public ArrayList<Shape> getInnerShapes() {
        return new ArrayList<>(this.shapes);
    }

    public int getWidth() {
        // remove toolbar width
        return this.model.getWidth() - Math.min(this.model.getWidth(), 30);
    }

    public int getHeight() {
        return this.model.getHeight();
    }

    public boolean isIn(int x, int y) {
        if (x > Math.min(this.model.getWidth(), 30) && x <= this.model.getWidth()) {
            return y >= 0 && y <= this.model.getHeight();
        }

        return false;
    }
}

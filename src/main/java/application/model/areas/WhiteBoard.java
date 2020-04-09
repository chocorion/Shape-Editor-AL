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

    private int x, y;
    private int width, height;

    public WhiteBoard(Model model) {
        this.shapes = new ArrayList<>();
        this.model = model;

        this.x = model.getToolBar().getX() + model.getToolBar().getWidth() + 1;
        this.y = model.getTopBar().getY() + model.getTopBar().getHeight() + 1;

        this.width = model.getWidth() - (model.getToolBar().getX() + model.getToolBar().getWidth()) - 2;
        this.height = model.getHeight() - (model.getTopBar().getY() + model.getTopBar().getHeight()) - 2;
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
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isIn(int x, int y) {
        if (x > model.getToolBar().getWidth() && x <= this.model.getWidth()) {
            return y >= 0 && y <= this.model.getHeight();
        }

        return false;
    }

    public Shape getShapeAt(int x, int y) {
        for (Shape shape : this.shapes) {
            if (shape.isIn(x, y)) {
                return shape;
            }
        }

        return null;
    }
}

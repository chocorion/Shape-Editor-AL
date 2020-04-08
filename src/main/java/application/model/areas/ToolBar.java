package application.model.areas;

import application.model.Model;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.ModelObservableImp;
import application.utils.ShapeContainer;
import application.utils.Color;

import java.util.ArrayList;

public class ToolBar extends ModelObservableImp implements ShapeContainer {
    private ArrayList<Shape> shapes;
    private Model model;

    private int x, y;
    private int width, height;

    public ToolBar(Model model) {
        this.shapes = new ArrayList<>();
        this.shapes.add(new Rectangle(6, model.getTopBar().getHeight() + 6, 33, 20, Color.BLUE));

        this.model = model;

        // Margin of 1
        this.x = 1;
        this.y = model.getTopBar().getHeight() + model.getTopBar().getY() + 1;
        this.width = 45;
        this.height = model.getHeight() - model.getTopBar().getHeight() + model.getTopBar().getY() - 4;
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
        this.shapes.remove(shape);
        this.update();
    }

    public ArrayList<Shape> getInnerShapes() {
        return new ArrayList<>(this.shapes);
    }

    public int getWidth() {
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
        if (x >= this.x && x <= this.x + this.getWidth()) {
            return y >= this.y && y <= this.y + this.getHeight();
        }

        return false;
    }

    // TODO Refactor
    public Shape getShape(int x, int y) {
        if (!this.isIn(x, y) || (y - model.getTopBar().getHeight()) / this.getWidth() >= this.shapes.size()) {
            return null;
        }

        return (Shape) this.shapes.get((y - model.getTopBar().getHeight()) / this.getWidth()).clone();
    }
}

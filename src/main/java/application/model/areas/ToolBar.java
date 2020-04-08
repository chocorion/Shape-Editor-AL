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

    public ToolBar(Model model) {
        this.shapes = new ArrayList<>();
        this.shapes.add(new Rectangle(0, 0, 40, 30, Color.BLUE));

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
        this.shapes.remove(shape);
        this.update();
    }

    public ArrayList<Shape> getInnerShapes() {
        return new ArrayList<>(this.shapes);
    }

    public int getWidth() {
        return Math.min(this.model.getWidth(), 30);
    }

    public int getHeight() {
        return this.model.getHeight();
    }

    public boolean isIn(int x, int y) {
        if (x >= 0 && x <= Math.min(this.model.getWidth(), 30)) {
            return y > this.model.getTopBar().getHeight() && y <= this.model.getHeight();
        }

        return false;
    }

    public Shape getShape(int x, int y) {
        if (!this.isIn(x, y) || y / Math.min(this.model.getWidth(), 30) >= this.shapes.size()) {
            return null;
        }

        return (Shape) this.shapes.get(y / Math.min(this.model.getWidth(), 30)).clone();
    }
}

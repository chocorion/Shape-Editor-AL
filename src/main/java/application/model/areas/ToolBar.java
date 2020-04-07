package application.model.areas;

import application.model.Model;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.ModelObservableImp;

import java.util.ArrayList;

public class ToolBar extends ModelObservableImp {
    private ArrayList<Shape> shapes;
    private Model model;

    public ToolBar(Model model) {
        this.shapes = new ArrayList<>();
        this.shapes.add(new Rectangle(0, 0, 40, 30));

        this.model = model;
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

    public int getWidth() {
        return Math.min(this.model.getWidth(), 30);
    }

    public int getHeight() {
        return this.model.getHeight();
    }
}

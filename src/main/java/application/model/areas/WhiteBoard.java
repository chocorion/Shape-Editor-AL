package application.model.areas;

import application.model.Model;
import application.model.shape.CompositeShape;
import application.model.shape.Shape;
import application.utils.ModelObservableImp;

import java.util.ArrayList;

public class WhiteBoard extends ModelObservableImp {
    private ArrayList<Shape> shapes;
    private Model model;

    public WhiteBoard(Model model) {
        this.shapes = new ArrayList<>();
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
        return this.model.getWidth();
    }

    public int getHeight() {
        return this.model.getHeight();
    }
}

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

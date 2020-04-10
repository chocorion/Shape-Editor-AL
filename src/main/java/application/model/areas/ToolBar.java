package application.model.areas;

import application.model.Model;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.model.shape.CompositeShape;
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
        this.shapes.add(new Rectangle(0, 0, 33, 20, Color.BLUE));
        this.shapes.add(new Rectangle(0, 0, 33, 50, Color.LIGHT_GREY));
        this.shapes.add(new Rectangle(0, 0, 65, 20, Color.BLACK));

        CompositeShape compositeShape = new CompositeShape();
        compositeShape.add(new Rectangle(0, 0, 50, 50, new Color(180, 100, 100)));
        compositeShape.add(new Rectangle(50, 50, 50, 50, new Color(180, 100, 100)));

        this.shapes.add(compositeShape);

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


    public Shape getShape(int shapeId) {
        if (shapeId < 0 || shapeId >= shapes.size())
            return null;

        return (Shape) this.shapes.get(shapeId).clone();
    }
}

package application.model.areas;

import application.model.Model;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.model.shape.CompositeShape;
import application.utils.ModelObservableImp;
import application.utils.Color;

import java.util.ArrayList;

public class ToolBar extends ModelObservableImp implements ShapeContainer {
    private final ArrayList<Shape> shapes;
    private Model model;

    public ToolBar(Model model) {
        shapes = new ArrayList<>();
        shapes.add(new Rectangle(0, 0, 33, 20, Color.BLUE));
        shapes.add(new Rectangle(0, 0, 33, 50, Color.LIGHT_GREY));
        shapes.add(new Rectangle(0, 0, 65, 20, Color.BLACK));

        CompositeShape compositeShape = new CompositeShape();
        compositeShape.add(new Rectangle(0, 0, 50, 50, new Color(180, 100, 100)));
        compositeShape.add(new Rectangle(50, 50, 50, 50, new Color(180, 100, 100)));

        shapes.add(compositeShape);

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


    public Shape getShape(int shapeId) {
        if (shapeId < 0 || shapeId >= shapes.size())
            return null;

        return (Shape) shapes.get(shapeId);
    }
}

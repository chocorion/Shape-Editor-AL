package application.model.areas;

import application.model.Memento.LoadFunction;
import application.model.Model;
import application.model.shape.Shape;
import application.utils.ModelObservableImp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class WhiteBoard extends ModelObservableImp implements ShapeContainer {
    private ArrayList<Shape> shapes;
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
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (shapes.get(i).isIn(x, y)) {
                return shapes.get(i);
            }
        }

        return null;
    }

    public void moveShape(Shape shape, double x, double y) {
        shape.moveTo(x, y);
        update();
    }

    public void replace(Set<Shape> oldShape, Set<Shape> newShape) {
        for (Shape s : oldShape) {
            removeShape(s);
        }

        for (Shape s : newShape) {
            addShape(s);
        }

        update();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Shape s : shapes) {
            str.append(s.toString());
        }
        return str.toString();
    }

    public void save(String path){
        File file = new File(path);
        try {
            FileWriter output = new FileWriter(file.getAbsoluteFile());
            output.write(toString());
            output.close();
        } catch (IOException e) {
            // File not found
            e.printStackTrace();
        }
    }
    public void load(String path){
        this.shapes = LoadFunction.loading(path);
        update();

    }

    public void toFirstPlan(Shape currentShape) {
        shapes.remove(currentShape);
        shapes.add(currentShape);
        update();
    }

    public int getShapePlace(Shape shape) {
        for (int i = 0; i < shapes.size(); i++) {
            if (shape == shapes.get(i)) {
                return i;
            }
        }

        return -1;
    }
}

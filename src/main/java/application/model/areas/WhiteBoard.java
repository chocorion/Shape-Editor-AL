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

/**
 * Represent the data of the whiteboard.
 */
public class WhiteBoard extends ModelObservableImp implements ShapeContainer {
    private ArrayList<Shape> shapes;
    private Model model;

    /**
     * Parameterized constructor.
     * @param model The current model of the application.
     */
    public WhiteBoard(Model model) {
        shapes = new ArrayList<>();
        this.model = model;
    }


    /**
     * Method called when there is a change in the whiteboard.
     * Notify observers.
     */
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


    /**
     * Return a copy of the inner list.
     * @return List of shape.
     */
    public ArrayList<Shape> getInnerShapes() {
        return new ArrayList<>(shapes);
    }


    /**
     * Return the "higher" shape at given position.
     * @param x X coords to look at.
     * @param y Y coords to look at.
     * @return The shape if exists, or null.
     */
    public Shape getShapeAt(int x, int y) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (shapes.get(i).isIn(x, y)) {
                return shapes.get(i);
            }
        }

        return null;
    }


    /**
     * Move the given shape in the whiteboard.
     * @param shape The shape to move.
     * @param x X coords of the shape destination.
     * @param y Y coords of the shape destination.
     */
    public void moveShape(Shape shape, double x, double y) {
        shape.moveTo(x, y);
        update();
    }


    /**
     * Replace shape by other in whiteboard.
     * @param oldShape Shape to replace.
     * @param newShape New shape for replacing old one.
     */
    public void replace(Set<Shape> oldShape, Set<Shape> newShape) {
        for (Shape s : oldShape) {
            removeShape(s);
        }

        for (Shape s : newShape) {
            addShape(s);
        }

        update();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Shape s : shapes) {
            str.append(s.toString());
        }
        return str.toString();
    }


    /**
     * Save the whiteboard.
     * @param path Path to the file te create.
     */
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


    /**
     * Load the file.
     * @param path Path to file to load.
     */
    public void load(String path){
        this.shapes = LoadFunction.loading(path);
        update();

    }


    /**
     * Move given shape to first plan.
     * @param shape shape to move at first plan.
     */
    public void toFirstPlan(Shape shape) {
        shapes.remove(shape);
        shapes.add(shape);
        update();
    }


    @Override
    public int getShapePlace(Shape shape) {
        for (int i = 0; i < shapes.size(); i++) {
            if (shape == shapes.get(i)) {
                return i;
            }
        }

        return -1;
    }
}

package application.model.areas;

import application.model.Memento.LoadFunction;
import application.model.Memento.ToolBarState;
import application.model.Model;
import application.model.shape.Polygon;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.model.shape.CompositeShape;
import application.utils.ModelObservableImp;
import application.utils.Color;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represent the data of the toolbar.
 */
public class ToolBar extends ModelObservableImp implements ShapeContainer {
    private ArrayList<Shape> shapes;
    private final Model model;

    /**
     * Parameterized constructor.
     */
    public ToolBar(Model model) {
        shapes = new ArrayList<>();
        this.model = model;
    }


    /**
     * Methods to call when a change is made in the toolbar.
     * Notify observers and save the current state.
     */
    public void update() {
        super.notifyObserver();
        this.model.hitSave();
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


    @Override
    public int getShapePlace(Shape shape) {
        for (int i = 0; i < shapes.size(); i++) {
            if (shape == shapes.get(i)) {
                return i;
            }
        }

        return -1;
    }


    /**
     * Return a copy of the array of shapes in the toolbar.
     * @return List of shape.
     */
    public ArrayList<Shape> getInnerShapes() {
        return new ArrayList<>(shapes);
    }


    public Shape getShape(int shapeId) {
        if (shapeId < 0 || shapeId >= shapes.size())
            return null;

        return  shapes.get(shapeId);
    }

    @Override
    public String toString() {
        StringBuilder txt = new StringBuilder("ToolBar \n");
        for (Shape s : shapes){
            txt.append(s.toString());
        }
        return txt.toString();
    }

    public ToolBarState save(){
        return new ToolBarState(toString());
    }

    public void restore(ToolBarState m){
        this.shapes = LoadFunction.loading( m.getState());
        update();

    }
}

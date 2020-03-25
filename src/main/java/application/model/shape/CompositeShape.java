package application.model.shape;

import application.view.View;

import java.util.HashSet;
import java.util.Set;

public class CompositeShape implements Shape, Cloneable {
    Set<Shape> shapeSet;

    public CompositeShape() {
        this.shapeSet = new HashSet<>();
    }

    @Override
    public void add(Shape shape) {
        this.shapeSet.add(shape);
    }

    @Override
    public void remove(Shape shape) {
        this.shapeSet.remove(shape);
    }

    @Override
    public void draw(View view) {
        for (Shape shape : this.shapeSet) {
            shape.draw(view);
        }
    }

    @Override
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch(CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }

        return o;
    }

}

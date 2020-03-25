package application.model.shape;

import java.util.HashSet;
import java.util.Set;

public class CompositeShape implements Shape, Cloneable {
    Set<Shape> shapeSet;

    public CompositeShape() {
        this.shapeSet = new HashSet<Shape>();
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

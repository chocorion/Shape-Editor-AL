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
    public int getWidth() {
        return this.getMaxX() - this.getMinX();
    }

    @Override
    public int getHeight() {
        return this.getMaxY() - this.getMinY();
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

    public int getMinX() {
        if (this.shapeSet.size() == 0) {
            return -1;
        }

        int minX = Integer.MAX_VALUE;

        for (Shape shape: this.shapeSet) {
            int shapeMinX = shape.getMinX();

            if (minX > shapeMinX) {
                minX = shapeMinX;
            }
        }

        return minX;
    }

    public int getMaxX() {
        if (this.shapeSet.size() == 0) {
            return -1;
        }

        int maxX = Integer.MIN_VALUE;

        for (Shape shape: this.shapeSet) {
            int shapeMaxX = shape.getMaxX();

            if (maxX < shapeMaxX) {
                maxX = shapeMaxX;
            }
        }

        return maxX;
    }

    public int getMinY() {
        if (this.shapeSet.size() == 0) {
            return -1;
        }

        int minY = Integer.MAX_VALUE;

        for (Shape shape: this.shapeSet) {
            int shapeMinY = shape.getMinY();

            if (minY > shapeMinY) {
                minY = shapeMinY;
            }
        }

        return minY;
    }

    public int getMaxY() {
        if (this.shapeSet.size() == 0) {
            return -1;
        }

        int maxY = Integer.MIN_VALUE;

        for (Shape shape: this.shapeSet) {
            int shapeMaxY = shape.getMaxY();

            if (maxY < shapeMaxY) {
                maxY = shapeMaxY;
            }
        }

        return maxY;
    }

    @Override
    public boolean isIn(int x, int y) {
        for (Shape shape:this.shapeSet) {
            if (shape.isIn(x, y)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void moveTo(int x, int y) {
        //TODO
        for (Shape shape:this.shapeSet) {
            shape.moveTo(x, y);
        }
    }

}

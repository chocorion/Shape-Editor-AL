package application.model.shape;

import application.utils.Color;
import application.view.ViewBridge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Represent a shape composed by the association of other shapes.
 */
public class CompositeShape implements Shape {
    ArrayList<Shape> shapeSet;

    /**
     * Default constructor.
     */
    public CompositeShape() {
        shapeSet = new ArrayList<>();
    }


    @Override
    public void add(Shape shape) {
        shapeSet.add(shape);
    }


    @Override
    public void remove(Shape shape) {
        shapeSet.remove(shape);
    }


    @Override
    public void draw(ViewBridge view) {
        for (Shape shape : shapeSet) {
            shape.draw(view);
        }
    }


    @Override
    public double getWidth() {
        return this.getMaxX() - this.getMinX();
    }


    @Override
    public double getHeight() {
        return this.getMaxY() - this.getMinY();
    }


    @Override
    public Object clone() {
        CompositeShape other = new CompositeShape();

        for (Shape shape : shapeSet) {
            other.add((Shape) shape.clone());
        }

        return other;
    }


    @Override
    public double getMinX() {
        if (this.shapeSet.size() == 0) {
            return -1;
        }

        double minX = Double.MAX_VALUE;

        for (Shape shape: this.shapeSet) {
            double shapeMinX = shape.getMinX();

            if (minX > shapeMinX) {
                minX = shapeMinX;
            }
        }

        return minX;
    }


    @Override
    public double getMaxX() {
        if (this.shapeSet.size() == 0) {
            return -1;
        }

        double maxX = Double.MIN_VALUE;

        for (Shape shape: this.shapeSet) {
            double shapeMaxX = shape.getMaxX();

            if (maxX < shapeMaxX) {
                maxX = shapeMaxX;
            }
        }

        return maxX;
    }


    @Override
    public double getMinY() {
        if (this.shapeSet.size() == 0) {
            return -1;
        }

        double minY = Double.MAX_VALUE;

        for (Shape shape: this.shapeSet) {
            double shapeMinY = shape.getMinY();

            if (minY > shapeMinY) {
                minY = shapeMinY;
            }
        }

        return minY;
    }


    @Override
    public double getMaxY() {
        if (this.shapeSet.size() == 0) {
            return -1;
        }

        double maxY = Double.MIN_VALUE;

        for (Shape shape: this.shapeSet) {
            double shapeMaxY = shape.getMaxY();

            if (maxY < shapeMaxY) {
                maxY = shapeMaxY;
            }
        }

        return maxY;
    }


    @Override
    public boolean isIn(double x, double y) {
        for (Shape shape:this.shapeSet) {
            if (shape.isIn(x, y)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean intersect(Rectangle rect) {
        double minX = getMinX();
        double maxX = getMaxX();
        double minY = getMinY();
        double maxY = getMaxY();


        boolean hoverlap = (minX < rect.getMaxX()) && (rect.getMinX() < minX + (maxX - minX));
        boolean voverlap = (minY < rect.getMaxY()) && (rect.getMinY() < minY + (maxY - minY));

        return hoverlap && voverlap;
    }


    @Override
    public void moveTo(double x, double y) {
        double minX = this.getMinX();
        double minY = this.getMinY();

        for (Shape shape : this.shapeSet) {
            shape.translate(x - minX,y - minY);
        }
    }


    @Override
    public void translate(double dx, double dy) {
        for (Shape shape:this.shapeSet) {
            shape.translate(dx, dy);
        }
    }


    @Override
    public void setAngle(double diffAngle) {
        //TODO
    }


    @Override
    public void resize(double factor) {
        for (Shape shape : this.shapeSet) {
            shape.resize(this, factor);
        }
    }


    @Override
    public void resize(Shape shapeContainer, double factor) {
        for (Shape shape : this.shapeSet) {
            shape.resize(shapeContainer, factor);
        }
    }


    @Override
    public void setColor(Color color) {
        for (Shape shape : shapeSet) {
            shape.setColor(color);
        }
    }


    @Override
    public Color getColor() {
        if (shapeSet.isEmpty()) return null;

        return shapeSet.iterator().next().getColor();
    }


    @Override
    public String toString() {
        StringBuffer string = new StringBuffer();
        string.append("CompositeShape ").append(shapeSet.size()).append("\n");

        for (Shape shape : this.shapeSet) {
            string.append(shape);
        }
        return string.toString();
    }


    /**
     * Return a copy of the shape list.
     * @return Arraylist of shapes.
     */
    public ArrayList<Shape> getShape(){
        return new ArrayList<>(this.shapeSet);
    }
}

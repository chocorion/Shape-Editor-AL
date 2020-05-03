package application.model.shape;

import application.exceptions.UnsupportedOperationException;
import application.view.View;

public interface Shape {
    void add(Shape shape)    throws UnsupportedOperationException;
    void remove(Shape shape) throws UnsupportedOperationException;

    void draw(View view);

    double getWidth();
    double getHeight();

    double getMinX();
    double getMinY();
    double getMaxX();
    double getMaxY();

    boolean isIn(double x, double y);
    boolean intersect(Rectangle rectangle);
    void moveTo(double x, double y);
    void translate(double dx, double dy);

    void resize(double factor);
    void resize(Shape containerShape, double factor);

    Object clone();
}

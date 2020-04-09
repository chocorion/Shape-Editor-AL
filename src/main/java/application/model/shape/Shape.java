package application.model.shape;

import application.exceptions.UnsupportedOperationException;
import application.view.View;

public interface Shape {
    void add(Shape shape) throws UnsupportedOperationException;
    void remove(Shape shape) throws UnsupportedOperationException;

    void draw(View view);

    int getWidth();
    int getHeight();

    int getMinX();
    int getMinY();
    int getMaxX();
    int getMaxY();

    boolean isIn(int x, int y);
    void moveTo(int x, int y);

    void resize(double factor);
    void resize(Shape containerShape, double factor);

    Object clone();
}

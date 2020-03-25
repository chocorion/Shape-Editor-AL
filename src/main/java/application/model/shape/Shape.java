package application.model.shape;

import application.exceptions.UnsupportedOperationException;
import application.view.View;

public interface Shape {
    void add(Shape shape) throws UnsupportedOperationException;
    void remove(Shape shape) throws UnsupportedOperationException;

    void draw(View view);

    Object clone();
}

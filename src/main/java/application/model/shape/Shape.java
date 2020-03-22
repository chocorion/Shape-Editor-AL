package application.model.shape;

import application.exceptions.UnsupportedOperationException;

public interface Shape {
    void add(Shape shape) throws UnsupportedOperationException;
    void remove(Shape shape) throws UnsupportedOperationException;

    Object clone();
}

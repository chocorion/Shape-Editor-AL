package application.model.areas;

import application.model.shape.Shape;

public interface ShapeContainer {
    void addShape(Shape shape);
    void removeShape(Shape shape);
}

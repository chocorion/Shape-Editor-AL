package application.model.areas;

import application.model.shape.Shape;

/**
 * Represent an object that contain shapes.
 */
public interface ShapeContainer {
    /**
     * Add a shape.
     * @param shape The shape to add.
     */
    void addShape(Shape shape);


    /**
     * Remove a shape if exists.
     * @param shape The shape to remove.
     */
    void removeShape(Shape shape);


    /**
     * Return the abstract position of the shape in the container.
     * @param shape The shape to get position.
     * @return Abstract position of the shape.
     */
    int getShapePlace(Shape shape);
}

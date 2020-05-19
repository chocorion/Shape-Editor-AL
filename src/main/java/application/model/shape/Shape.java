package application.model.shape;

import application.exceptions.UnsupportedOperationException;
import application.utils.Color;
import application.view.ViewBridge;

/**
 * Interface for a shape in model.
 */
public interface Shape extends Cloneable {
    /**
     * Add a shape in shape implementation.
     * @param shape The shape to add.
     * @throws UnsupportedOperationException when the shape implementation can't be composed by other shape..
     */
    void add(Shape shape)    throws UnsupportedOperationException;


    /**
     * Remove a shape in the shape implementation.
     * @param shape The shape to remove.
     * @throws UnsupportedOperationException When the shape implementation can't be composed by other shape.
     */
    void remove(Shape shape) throws UnsupportedOperationException;


    /**
     * Draw the shape using the given view.
     * @param view The view to use for drawing.
     */
    void draw(ViewBridge view);


    /**
     * Return the width of the shape.
     * @return Width of the shape.
     */
    double getWidth();


    /**
     * Return the height of the shape.
     * @return Height of the shape.
     */
    double getHeight();


    /**
     * Return the minimal x coords of the shape.
     * @return Minimal x coords of the shape.
     */
    double getMinX();


    /**
     * Return the minimal y coords of the shape.
     * @return Minimal y coords of the shape.
     */
    double getMinY();


    /**
     * Return the maximal x coords of the shape.
     * @return Maximal x coords of the shape.
     */
    double getMaxX();


    /**
     * Return the maximal y coords of the shape.
     * @return Maximal y coords of the shape.
     */
    double getMaxY();


    /**
     * Return if a point is include in the shape.
     * @param x X coords of the point.
     * @param y Y coords of the point.
     * @return True if the point is included, else, false.
     */
    boolean isIn(double x, double y);


    /**
     * Return if the shape intersect the given rectangle.
     * @param rectangle Rectangle to check, angle of this rectangle must be 0.
     * @return True if the shape intersect, else false;
     */
    boolean intersect(Rectangle rectangle);


    /**
     * Move the top left of the shape to given position.
     * @param x X coords of the destination.
     * @param y Y coords of the destination.
     */
    void moveTo(double x, double y);


    /**
     * Translate the shape.
     * @param dx Value for the x translation.
     * @param dy Value for the y translation.
     */
    void translate(double dx, double dy);


    /**
     * Set the angle of the shape.
     * @param newAngle The new angle of the shape. Must be between 0 and 360.
     */
    void setAngle(double newAngle);


    /**
     * Resize the current shape.
     * @param factor The factor for the resize, must be greater than zero.
     */
    void resize(double factor);


    /**
     * Resize the current shape in another shape. move the shape to the good position according to resize.
     * @param factor The factor for the resize, must be greater than zero.
     */
    void resize(Shape containerShape, double factor);


    /**
     * Set the color of the shape.
     * @param color New color of the shape.
     */
    void setColor(Color color);


    /**
     * Return the color of the shape.
     * @return Color of the shape.
     */
    Color getColor();


    /**
     * Return a clone of the shape.
     * @return Clone of the shape.
     */
    Object clone();
}

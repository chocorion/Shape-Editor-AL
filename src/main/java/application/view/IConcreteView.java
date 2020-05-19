package application.view;

import application.utils.Color;

/**
 * Interface for creating an implementation of the view usable by the application.
 */
public interface IConcreteView {
    /**
     * Draw a basic rectangle.
     * @param x Top left x coords.
     * @param y Top left y coords.
     * @param width Width of the rectangle in pixels.
     * @param height Height of the rectangle in pixels.
     * @param color Color of the rectangle.
     */
    void devDrawRectangle(int x, int y, int width, int height, Color color);


    /**
     * Draws a basic rectangle, but only the edges, width a stoke of one pixel.
     * @param x Top left x coords.
     * @param y Top left y coords.
     * @param width Width of the rectangle in pixels.
     * @param height Height of the rectangle in pixels.
     * @param color Color of the rectangle.
     */
    void devDrawStrokeRectangle(int x, int y, int width, int height, Color color);


    /**
     * Draws a basic polygon.
     * @param x Array of x coords for the points.
     * @param y Array of y coords for the points.
     * @param color Color of the polygon.
     */
    void devDrawPolygon(double[] x, double[] y, Color color);


    /**
     * Draws an image.
     * @param path Path to the image file.
     * @param x Top left x coords of the image.
     * @param y Top left y coords of the image.
     * @param width Width of the image in pixels.
     * @param height Height of the image in pixels.
     */
    void devDrawImage(String path, int x, int y, int width, int height);


    /**
     * Draws a basic rectangle with round corners.
     * @param x Top left x coords.
     * @param y Top left y coords.
     * @param width Width of the rectangle in pixels.
     * @param height Height of the rectangle in pixels.
     * @param arcWidth Width of the corner round in pixels.
     * @param arcHeight Height of the corner round in pixels.
     * @param color Color of the rectangle.
     */
    void devDrawRoundedRect(int x, int y, int width, int height, int arcWidth, int arcHeight, Color color);


    /**
     * Draws text.
     * @param text Text to draw
     * @param x Top left x coords of the text.
     * @param y Top left y coords of the text.
     * @param size Maximum width of the text.
     * @param color Color of the text.
     */
    void devDrawText(String text, int x, int y, int size, Color color);


    /**
     * Draws the save menu in a new windows.
     */
    void devDrawSaveMenu();


    /**
     * Draws the load menu in a new windows.
     */
    void devDrawLoadMenu();


    /**
     * Draws a basic rectangle with round corners.
     * @param x Top left x coords.
     * @param y Top left y coords.
     * @param width Width of the rectangle in pixels.
     * @param height Height of the rectangle in pixels.
     * @param arcWidth Width of the corner round in pixels.
     * @param arcHeight Height of the corner round in pixels.
     * @param angle Angle of the rectangle in degrees.
     * @param color Color of the rectangle.
     */
    void devDrawRoundedRectRotation(int x, int y, int width, int height, int arcWidth, int arcHeight, int angle, Color color);
}

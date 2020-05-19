package application.view;

import application.model.shape.Polygon;
import application.model.shape.Rectangle;
import application.utils.Color;
import application.utils.Pair;

/**
 * Represent the bridge to the view implementation.
 */
public class ViewBridge {
    private IConcreteView implementation;

    /**
     * Parameterized constructor.
     * @param concreteView Implementation to use for drawing.
     */
    public ViewBridge(IConcreteView concreteView) {
        implementation = concreteView;
    }


    /**
     * Draws a simple rectangle.
     * @param x Top left x coords of the rectangle.
     * @param y Top left y coords of the rectangle.
     * @param width Width of the rectangle in pixels.
     * @param height Height of the rectangle in pixels.
     * @param color Color of the rectangle.
     */
    public void drawRectangle(int x, int y, int width, int height, Color color) {
        implementation.devDrawRectangle(x, y, width, height, color);
    }


    /**
     * Draw a basic rectangle.
     * @param rectangle The rectangle to draw.
     */
    public void drawRectangle(Rectangle rectangle) {
        implementation.devDrawRectangle(
                (int) rectangle.getMinX(),
                (int) rectangle.getMinY(),
                (int) rectangle.getWidth(),
                (int) rectangle.getHeight(),
                rectangle.getColor()
        );
    }


    /**
     * Draws a simple rectangle, but only the edges.
     * @param x Top left x coords of the rectangle.
     * @param y Top left y coords of the rectangle.
     * @param width Width of the rectangle in pixels.
     * @param height Height of the rectangle in pixels.
     * @param color Color of the rectangle.
     */
    public void drawStrokeRectangle(int x, int y, int width, int height, Color color) {
        implementation.devDrawStrokeRectangle(x, y, width, height, color);
    }


    /**
     * Draws a simple rectangle, but only the edges.
     * @param rectangle The rectangle to draw.
     */
    public void drawStrokeRectangle(Rectangle rectangle) {
        implementation.devDrawStrokeRectangle(
                (int) rectangle.getMinX(),
                (int) rectangle.getMinY(),
                (int) rectangle.getWidth(),
                (int) rectangle.getHeight(),
                rectangle.getColor()
        );
    }


    /**
     * Draws a basic polygon.
     * @param polygon The polygon to draw.
     */
    public void drawPolygon(Polygon polygon) {
        Pair<double[], double[]> points = polygon.getPoints();
        implementation.devDrawPolygon(points.getKey(), points.getValue(), polygon.getColor());
    }


    /**
     * Draws an image
     * @param path Path to the image to draw.
     * @param r Rectangle representing the surface of the image.
     */
    public void drawImage(String path, Rectangle r) {
        implementation.devDrawImage(
                path,
                (int) r.getMinX(),
                (int) r.getMinY(),
                (int) r.getWidth(),
                (int) r.getHeight()
        );
    }


    /**
     * Draws text.
     * @param text the text to draw.
     * @param x Top left x coords.
     * @param y Top left y coords.
     * @param size Maximal width taken by the text.
     * @param color Color of the texte.
     */
    public void drawText(String text, int x, int y, int size, Color color) {
        implementation.devDrawText(text, x, y, size, color);
    }


    /**
     * Draws the whiteboard selection rectangle.
     * @param x Top left x coords of the selection.
     * @param y Top left y coords of the selection.
     * @param width Width of the selection in pixels.
     * @param height Height of the selection in pixels.
     */
    public void drawSelection(int x, int y, int width, int height) {
        implementation.devDrawStrokeRectangle(x, y, width, height, Color.BLUE);
    }


    /**
     * Draws rounded rectangle, with black shadow around.
     * @param x Top left x coords.
     * @param y Top left y coords.
     * @param width Width in pixels.
     * @param height Height in pixels.
     * @param roundSize Size of the corner in pixels.
     * @param shadowSize Size of the shadow in pixels.
     * @param color Color of the rectangle.
     */
    public void drawRoundedRectShadow(int x, int y, int width, int height, int roundSize, int shadowSize, Color color) {
        implementation.devDrawRoundedRect(
                x - shadowSize,
                y - shadowSize,
                width + 2 * shadowSize,
                height + 2 * shadowSize,
                roundSize + shadowSize,
                roundSize + shadowSize,
                new Color(30, 30, 30, 0.4)
        );

        implementation.devDrawRoundedRect(x, y, width, height, roundSize, roundSize, color);
    }


    /**
     * Draws a basic rounded rectangle.
     * @param x Top left x coords.
     * @param y Top left y coords.
     * @param width Width in pixels.
     * @param height Height in pixels.
     * @param roundSize Size of the corner in pixels.
     * @param color Color of the rectangle.
     */
    public void drawRoundedRect(int x, int y, int width, int height, int roundSize, Color color) {
        implementation.devDrawRoundedRect(x, y, width, height, roundSize, roundSize, color);
    }


    /**
     * Draws a basic rounded rectangle.
     * @param r the rectangle to draw.
     */
    public void drawRoundedRect(Rectangle r) {
        implementation.devDrawRoundedRectRotation(
                (int) r.getMinX(),
                (int) r.getMinY(),
                (int) r.getWidth(),
                (int) r.getHeight(),
                (int) r.getRoundValue(),
                (int) r.getRoundValue(),
                (int) r.getAngle(),
                r.getColor()
        );
    }


    /**
     * Draws the whiteboard save menu.
     */
    public void drawSaveMenu() {
        implementation.devDrawSaveMenu();
    }


    /**
     * Draws the whiteboard load menu.
     */
    public void drawLoadMenu(){
        implementation.devDrawLoadMenu();
    }

}

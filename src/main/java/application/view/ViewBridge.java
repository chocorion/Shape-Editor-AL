package application.view;

import application.model.shape.Polygon;
import application.model.shape.Rectangle;
import application.utils.Color;
import application.utils.Pair;

public class ViewBridge {
    private IConcreteView implementation;

    public ViewBridge(IConcreteView concreteView) {
        implementation = concreteView;
    }

    public void drawRectangle(int x, int y, int width, int height, Color color) {
        implementation.devDrawRectangle(x, y, width, height, color);
    }

    public void drawRectangle(Rectangle rectangle) {
        implementation.devDrawRectangle(
                (int) rectangle.getMinX(),
                (int) rectangle.getMinY(),
                (int) rectangle.getWidth(),
                (int) rectangle.getHeight(),
                rectangle.getColor()
        );
    }

    public void drawStrokeRectangle(int x, int y, int width, int height, Color color) {
        implementation.devDrawStrokeRectangle(x, y, width, height, color);
    }

    public void drawStrokeRectangle(Rectangle rectangle) {
        implementation.devDrawStrokeRectangle(
                (int) rectangle.getMinX(),
                (int) rectangle.getMinY(),
                (int) rectangle.getWidth(),
                (int) rectangle.getHeight(),
                rectangle.getColor()
        );
    }


    public void drawPolygon(Polygon polygon) {
        Pair<double[], double[]> points = polygon.getPoints();
        implementation.devDrawPolygon(points.getKey(), points.getValue(), polygon.getColor());
    }


    public void drawImage(String path, Rectangle r) {
        implementation.devDrawImage(
                path,
                (int) r.getMinX(),
                (int) r.getMinY(),
                (int) r.getWidth(),
                (int) r.getHeight()
        );
    }


    public void drawText(String text, int x, int y, int size, Color color) {
        implementation.devDrawText(text, x, y, size, color);
    }

    public void drawSelection(int x, int y, int width, int height) {
        implementation.devDrawStrokeRectangle(x, y, width, height, Color.BLUE);
    }

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

    public void drawRoundedRect(int x, int y, int width, int height, int roundSize, Color color) {
        implementation.devDrawRoundedRect(x, y, width, height, roundSize, roundSize, color);
    }

    public void drawSaveMenu() {
        implementation.devDrawSaveMenu();
    }

    public void drawLoadMenu(){
        implementation.devDrawLoadMenu();
    }

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
}

package application.view;

import application.model.shape.Polygon;
import application.model.shape.Rectangle;
import application.ui.javafx.ViewFx;
import application.utils.Color;

public class ViewBridge {
    private ConcreteViewItf implementation;

    public ViewBridge(ConcreteViewItf concreteView) {
        implementation = concreteView;
    }

    public void drawRectangle(int x, int y, int width, int height, Color color) {
        implementation.devDrawRectangle(x, y, width, height, color);
    }

    public void drawRectangle(Rectangle rectangle) {
        implementation.devDrawRectangle(
                (int) rectangle.getX(),
                (int) rectangle.getY(),
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
                (int) rectangle.getX(),
                (int) rectangle.getY(),
                (int) rectangle.getWidth(),
                (int) rectangle.getHeight(),
                rectangle.getColor()
        );
    }


    public void drawPolygon(Polygon polygon) {
        implementation.devDrawPolygon(polygon);
    }


    public void drawImage(String path, Rectangle r) {
        implementation.devDrawImage(
                path,
                (int) r.getX(),
                (int) r.getY(),
                (int) r.getWidth(),
                (int) r.getHeight()
        );
    }


    public void drawText(String text, int x, int y, int size, Color color) {
        this.implementation.devDrawText(text, x, y, size, color);
    }

    public void drawSelection(int x, int y, int width, int height) {
        implementation.devDrawStrokeRectangle(x, y, width, height, Color.BLUE);
    }
}

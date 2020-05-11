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


    public void drawRectangle(Rectangle rectangle) {
        implementation.devDrawRectangle(rectangle);
    }


    public void drawStrokeRectangle(Rectangle rectangle) {
        implementation.devDrawStrokeRectangle(rectangle);
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
        implementation.devDrawStrokeRectangle(new Rectangle(x, y, width, height, Color.BLUE));
    }
}

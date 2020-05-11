package application.view;

import application.model.shape.Polygon;
import application.model.shape.Rectangle;
import application.utils.Color;

public interface ConcreteViewItf {
    int getWidth();
    int getHeight();

    void devDrawRectangle(Rectangle rectangle);
    void devDrawStrokeRectangle(Rectangle rectangle);
    void devDrawPolygon(Polygon polygon);

    void devDrawImage(String path, int x, int y, int width, int height);

    void devDrawText(String text, int x, int y, int size, Color color);
}

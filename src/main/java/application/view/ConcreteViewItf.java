package application.view;

import application.model.shape.Polygon;
import application.model.shape.Rectangle;

public interface ConcreteViewItf {
    int getWidth();
    int getHeight();

    void devDrawRectangle(Rectangle rectangle);
    void devDrawPolygon(Polygon polygon);
}

package application.view;

import application.model.shape.Polygon;
import application.model.shape.Rectangle;

public interface View {
    void draw();

    void drawRectangle(Rectangle rectangle);
    void drawPolygon(Polygon polygon);
}

package application.view;

import application.model.shape.Polygon;
import application.model.shape.Rectangle;
import application.utils.Color;

public interface View {
    void draw();

    int getWidth();
    int getHeight();

    void drawRectangle(Rectangle rectangle);
    void drawStrokeRectangle(Rectangle rectangle);

    void drawPolygon(Polygon polygon);
    void drawSelection(int x, int y, int width, int height);

    void drawImage(String path, Rectangle rect);

    void drawText(String text, int x, int y, int size, Color color);
}

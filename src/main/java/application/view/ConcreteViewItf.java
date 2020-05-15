package application.view;

import application.model.shape.Polygon;
import application.utils.Color;

public interface ConcreteViewItf {
    void devDrawRectangle(int x, int y, int width, int height, Color color);
    void devDrawStrokeRectangle(int x, int y, int width, int height, Color color);

    void devDrawPolygon(Polygon polygon);

    void devDrawImage(String path, int x, int y, int width, int height);
    void devDrawRoundedRect(int x, int y, int width, int height, int arcWidth, int arcHeight, Color color);
    void devDrawText(String text, int x, int y, int size, Color color);

    void devDrawSaveMenu();

    void devDrawLoadMenu();
}

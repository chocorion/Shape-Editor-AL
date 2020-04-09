package application.view;

import application.model.shape.Polygon;
import application.model.shape.Rectangle;

public interface ConcreteViewItf {
    int getWidth();
    int getHeight();

    void devDrawRectangle(Rectangle rectangle);
    void devDrawPolygon(Polygon polygon);
    void devDrawSelection(int x, int y, int width, int height);
    void devAddPopUpMenu(int x, int y);
    boolean devClickOnGroup(int x,int y);
    void devUndrawSelect(int beginX, int beginY, int endX, int endY);
    void devUndrawMenu();
}

package application.view;

import application.model.shape.Polygon;
import application.model.shape.Rectangle;

public interface View {
    void draw();

    int getWidth();
    int getHeight();

    void drawRectangle(Rectangle rectangle);
    void drawPolygon(Polygon polygon);
    void drawSelection(int x, int y, int width, int height);

    void addPopUpMenu(int x, int y);

    boolean clickOnGroup(int x,int y);

    void undrawSelect(int x, int y, int width, int height);
    void undrawMenu();
}

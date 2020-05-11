package application.view.decoration.menu;

import application.model.shape.Rectangle;
import application.utils.Color;
import application.view.ViewBridge;

public class subMenuColor implements SubMenu {
    private int x, y, width, height;
    private Rectangle area;
    private ViewBridge view;

    public subMenuColor(ViewBridge view, int x, int y, int width, int height) {
        this.view = view;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.area = new Rectangle(x, y, width, height, Color.BLUE);
    }

    @Override
    public void draw(int x, int y) {
        System.out.println("Area x : " + area.getX() + ", area y : " + area.getY());
        view.drawStrokeRectangle(this.area);
    }

    @Override
    public String toString() {
        return "Color";
    }
}

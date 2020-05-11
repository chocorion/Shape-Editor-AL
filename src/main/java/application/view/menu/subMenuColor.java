package application.view.menu;

import application.model.shape.Rectangle;
import application.utils.Color;
import application.view.ViewBridge;

public class subMenuColor implements EditionSubMenu {
    private int x, y, width, height;
    private Rectangle area;
    private ViewBridge view;

    public subMenuColor(ViewBridge view, int x, int y, int width, int height) {
        this.view = view;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(int x, int y) {
        view.drawStrokeRectangle(x, y, width, height, Color.BLUE);
    }

    @Override
    public String getName() {
        return "color";
    }
}

package application.view.menu;

import application.model.shape.Rectangle;
import application.utils.Color;
import application.view.ViewBridge;

public class WhiteBoardMenu {
    private static String[] items = {
            "Group",
            "Ungroup",
            "Edit"
    };


    private static int width  = 60;
    private static int height = 20;

    private ViewBridge view;

    private int x, y;

    public WhiteBoardMenu(ViewBridge view) {
        this.view = view;
    }


    public void draw(int x, int y) {
        this.x = x;
        this.y = y;

        // Draw menu here
        for (int i = 0; i < items.length; i++) {
            drawMenuItem(i);
        }
    }

    private void drawMenuItem(int itemId) {
        Rectangle menuRect = new Rectangle(this.x, this.y + itemId * height, width, height, Color.LIGHT_GREY);
        view.drawRectangle(menuRect);

        menuRect.setColor(Color.BLACK);
        view.drawStrokeRectangle(menuRect);
        view.drawText(items[itemId], x + 2, (int) (y + (itemId + 0.8) * (height)), width, Color.BLACK);
    }

    public int getItemId(int x, int y) {
        if (x < this.x || x > this.x + width) {
            return -1;
        }

        int diff = y - this.y;
        int itemId = diff/height;

        if (itemId < 0 || itemId >= items.length) {
            itemId = -1;
        }

        return itemId;
    }

    public boolean isIn(int x, int y) {
        return getItemId(x, y) != -1;
    }
}
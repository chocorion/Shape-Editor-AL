package application.view.menu;

import application.model.shape.Rectangle;
import application.utils.Color;
import application.view.ViewBridge;

public class WhiteBoardMenu {
    private static final String[] items = {
            "Group",
            "Ungroup",
            "Edit"
    };


    private static final int width  = 60;
    private static final int height = 20;

    private final ViewBridge view;

    private int x, y;
    private int currentySelected;

    public WhiteBoardMenu(ViewBridge view) {
        this.view = view;
        currentySelected = -1;
    }


    public void draw(int x, int y) {
        this.x = x;
        this.y = y;

        view.drawRoundedRectShadow(x, y, width, items.length * height, 20, 3, Color.WHITE);
        drawWithoutShadow(x, y);
    }

    public void drawWithoutShadow(int x, int y) {
        view.drawRoundedRect(x, y, width, items.length * height, 20, Color.WHITE);

        if (currentySelected != -1) {
            view.drawRoundedRect(x, y + currentySelected * height, width, height, 20, new Color(156, 221, 255));
        }
        for (int i = 0; i < items.length; i++) {
            if (i == currentySelected)
                view.drawText(items[i], x + 2, (int) (y + (i + 0.8) * (height)), width, Color.WHITE);
            else
                view.drawText(items[i], x + 2, (int) (y + (i + 0.8) * (height)), width, Color.BLACK);
        }
    }


    public void setCurrentySelected(int id) {
        int old = currentySelected;
        currentySelected = id;

        if (id != old)
            drawWithoutShadow(x, y);
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
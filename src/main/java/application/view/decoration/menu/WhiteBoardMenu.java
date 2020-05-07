package application.view.decoration.menu;

import application.model.shape.Rectangle;
import application.utils.Color;
import application.view.ObserverDecoration;
import application.view.decoration.menu.Menu;

public class WhiteBoardMenu extends Menu {
    private static String[] items = {
            "Group",
            "Ungroup",
            "Edit"
    };

    private EditionMenu editionMenu;

    private static int width  = 60;
    private static int height = 20;

    public WhiteBoardMenu(ObserverDecoration decoration) {
        super(decoration);

        editionMenu = new EditionMenu(decoration);
    }

    @Override
    public void update() {
        if (editionMenu.isToggle())
            editionMenu.update();
        else
            this.draw();
    }

    @Override
    public void draw() {
        super.draw();

        // Draw menu here
        for (int i = 0; i < items.length; i++) {
            drawMenuItem(i);
        }

    }

    private void drawMenuItem(int itemId) {
        Rectangle menuRect = new Rectangle(this.x, this.y + itemId * height, width, height, Color.LIGHT_GREY);
        super.drawRectangle(menuRect);

        menuRect.setColor(Color.BLACK);
        super.drawStrokeRectangle(menuRect);
        super.drawText(items[itemId], x + 2, (int) (y + (itemId + 0.8) * (height)), width, Color.BLACK);
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
        if (editionMenu.isToggle()) {
            return editionMenu.isIn(x, y);
        }
        return getItemId(x, y) != -1;
    }

    public void openEditionMenu() {
        close();
        editionMenu.open(x, y);
    }

    public void closeEditionMenu() {
        editionMenu.close();
        open(x, y);
    }
}
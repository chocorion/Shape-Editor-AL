package application.view.decoration;

import application.model.shape.Rectangle;
import application.utils.Color;
import application.view.ObserverDecoration;

public class WhiteBoardMenu extends Menu {
    private static String[] items = {
            "Group",
            "Ungroup",
            "Edit"
    };

    private static int width  = 60;
    private static int height = 20;

    public WhiteBoardMenu(ObserverDecoration decoration) {
        super(decoration);
    }

    @Override
    public void update() {
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
        super.drawText(items[itemId], x, y + itemId * height, width, Color.BLACK);
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
}

/*

    @Override
    public void devAddPopUpMenu(int x, int y){
        this.menuX =x;
        this.menuY =y;
        System.out.println("menu");
        int coeffW = this.getWidth() / this.model.getWidth();
        int coeffH = this.getHeight() / this.model.getHeight();

        int whiteBoardW = this.model.getWhiteBoard().getWidth();
        int whiteBoardH = this.model.getWhiteBoard().getHeight();

        this.viewWhiteBoardW = whiteBoardW*coeffW;
        this.viewWhiteBoardH = whiteBoardH*coeffH;

        int width =viewWhiteBoardW /20;
        int height = viewWhiteBoardH/40;

        for (int index=0; index< this.menuName.size(); index ++){
            drawMenuItem(width,height,index);
        }

    }

 */
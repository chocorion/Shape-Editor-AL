package application.view.element;

import application.utils.Color;
import application.view.IDrawable;
import application.view.ViewBridge;

public class TextButton implements IDrawable {
    private int x, y, width, height;
    private String txt;
    private boolean pushed;

    private ViewBridge view;

    public TextButton(ViewBridge view, int x, int y, int width, int height, String txt) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.txt = txt;
        this.view = view;
        pushed = false;
    }

    @Override
    public void draw(int xOffset, int yOffset) {
        // Put in two separated functions
        if (!pushed) {
            view.drawRoundedRectShadow(x + xOffset, y + yOffset, width, height, 20, 2, Color.WHITE);
            view.drawText(txt, x + xOffset, y + yOffset + (int) (height * 0.6), (int) (width * 0.9), Color.BLACK);
        }

        else {
            view.drawRoundedRectShadow(x + xOffset, y + yOffset, width, height, 20, 2, Color.LIGHT_GREY);
            view.drawText(txt, x + xOffset, y + yOffset+ (int) (height * 0.6), (int) (width * 0.9), Color.BLACK);
        }
    }

    public void push() {
        pushed = true;
    }

    public void unpush() {
        pushed = false;
    }


    public boolean isIn(int x, int y) {
        if (x >= this.x && x <= this.x + width) {
            return y >= this.y && y <= this.y + height;
        }

        return false;
    }
}

package application.view.element;

import application.utils.Color;
import application.view.Drawable;
import application.view.ViewBridge;
import application.view.element.interaction.TextInputInteraction;

public class TextInput implements Drawable {
    private int x, y;
    private int width, height;

    private StringBuffer txt;
    private ViewBridge view;

    public TextInput(ViewBridge view, int x, int y, int width, int height) {
        this.view = view;
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        txt = new StringBuffer();
    }

    public void append(String s) {
        txt.append(s);
    }

    public void remove() {
        if (txt.length() > 0)
            txt.deleteCharAt(txt.length() - 1);
    }

    @Override
    public void draw(int x, int y) {
        view.drawRoundedRectShadow(this.x + x, this.y + y, this.width, this.height, 3, 2, Color.WHITE);
        view.drawText(txt.toString(), this.x + x, this.y + y + (int) (height * 0.8), width, Color.BLACK);
    }

    public boolean isIn(int x, int y) {
        if (x >= this.x && x <= this.x + width) {
            return y >= this.y && y <= this.y + height;
        }

        return false;
    }

    public String getText() {
        return txt.toString();
    }

    public TextInputInteraction getInteraction() {
        return new TextInputInteraction(this);
    }
}

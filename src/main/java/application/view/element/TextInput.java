package application.view.element;

import application.utils.Color;
import application.view.IConcreteView;
import application.view.IDrawable;
import application.view.ViewBridge;
import application.view.element.interaction.TextInputInteraction;


/**
 * Represent a basic graphical text input.
 */
public class TextInput extends ViewBridge implements IDrawable {
    private int x, y;
    private int width, height;

    private StringBuffer txt;

    /**
     * Parameterized constructor.
     * @param view Implementation to use for drawing.
     * @param x Top left x coords.
     * @param y Top left y coords.
     * @param width Width of the text input.
     * @param height Height of the text input.
     */
    public TextInput(IConcreteView view, int x, int y, int width, int height) {
        super(view);
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        txt = new StringBuffer();
    }


    /**
     * Appends string to the text displayed.
     * @param s String to add.
     */
    public void append(String s) {
        txt.append(s);
    }


    /**
     * Deletes one character to the current text.
     */
    public void remove() {
        if (txt.length() > 0)
            txt.deleteCharAt(txt.length() - 1);
    }


    @Override
    public void draw(int x, int y) {
        drawRoundedRectShadow(this.x + x, this.y + y, this.width, this.height, 3, 2, Color.WHITE);
        drawText(txt.toString(), this.x + x, this.y + y + (int) (height * 0.8), width, Color.BLACK);
    }


    /**
     * Returns if a point is in the text input.
     * @param x X coords of the point.
     * @param y Y coords of the point.
     * @return true if the point is in, else false.
     */
    public boolean isIn(int x, int y) {
        if (x >= this.x && x <= this.x + width) {
            return y >= this.y && y <= this.y + height;
        }

        return false;
    }


    /**
     * Returns the current text.
     * @return Current text.
     */
    public String getText() {
        return txt.toString();
    }


    /**
     * Returns the available interactions for this graphical element.
     * @return Available interactions.
     */
    public TextInputInteraction getInteraction() {
        return new TextInputInteraction(this);
    }
}

package application.view.element;

import application.utils.Color;
import application.view.IConcreteView;
import application.view.IDrawable;
import application.view.ViewBridge;


/**
 * Represent a basic graphical button, with text inside.
 */
public class Button extends ViewBridge implements IDrawable {
    private int x, y, width, height;
    private String txt;
    private boolean pushed;

    /**
     * Parameterized constructor.
     * @param view Implementation to use for drawing.
     * @param x Top left x coords.
     * @param y Top left y coords.
     * @param width Width of the button.
     * @param height height of the button.
     * @param txt Text to display in the button.
     */
    public Button(IConcreteView view, int x, int y, int width, int height, String txt) {
        super(view);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.txt = txt;
        pushed = false;
    }


    @Override
    public void draw(int xOffset, int yOffset) {
        // Put in two separated functions
        if (!pushed) {
            drawRoundedRectShadow(x + xOffset, y + yOffset, width, height, 20, 2, Color.WHITE);
            drawText(txt, x + xOffset, y + yOffset + (int) (height * 0.6), (int) (width * 0.9), Color.BLACK);
        }

        else {
            drawRoundedRectShadow(x + xOffset, y + yOffset, width, height, 20, 2, Color.LIGHT_GREY);
            drawText(txt, x + xOffset, y + yOffset+ (int) (height * 0.6), (int) (width * 0.9), Color.BLACK);
        }
    }


    /**
     * Changes the appearance of the button to pushed button.
     */
    public void push() {
        pushed = true;
    }


    /**
     * Changes the appearance of the button to un-pushed button.
     */
    public void unpush() {
        pushed = false;
    }


    /**
     * Returns if a point is in the button.
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
}

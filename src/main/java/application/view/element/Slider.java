package application.view.element;

import application.utils.Color;
import application.view.IDrawable;
import application.view.ViewBridge;


/**
 * Represent a basic graphical slider.
 */
public class Slider implements IDrawable {
    private int width, height;
    private int x, y;
    private int buttonX, buttonY, buttonW, buttonH;

    private double value;
    private ViewBridge view;

    /**
     * Parameterized constructor.
     * @param view Bridge to use for drawing.
     * @param x Top left x coords.
     * @param y Top left y coords.
     * @param width Width of the principal bar.
     * @param height Height of the principal bar.
     * @param initialValue Initial value of the slider, impact the position of the button. Must be between 0 and 1.
     * @param buttonWidth Width of the button.
     * @param buttonHeight Height of the button.
     */
    public Slider(ViewBridge view, int x, int y, int width, int height, double initialValue, int buttonWidth, int buttonHeight) {
        this.view = view;
        this.x = x;
        this.y = y;
        value = initialValue;

        buttonW = buttonWidth;
        buttonH = buttonHeight;

        buttonX = x - buttonW/2 + (int) (width * value);
        buttonY = y - buttonH/2;

        this.width = width;
        this.height = height;
    }


    @Override
    public void draw(int offsetX, int offsetY) {
        view.drawRectangle(x + offsetX, y + offsetY - height/2, width, height, Color.BLACK);

        view.drawRoundedRect(
                offsetX + buttonX,
                offsetY + buttonY,
                buttonW,
                buttonH,
                5,
                Color.BLUE
        );
    }


    /**
     * Moves the button to the given x coords, relatively to the current inner x.
     * @param x New x coords for the button.
     */
    public void moveButton(int x) {
        if (x < this.x)
            buttonX = this.x - buttonW/2;

        else if (x > this.x + width)
            buttonX = this.x + width - buttonW/2;

        else
            buttonX = x - buttonW/2;


        value = ((buttonX + buttonW/2 - this.x))/(double)width;
    }


    /**
     * Updates the current value, and moves the button to correct position.
     * @param value The new value for the slider.
     */
    public void moveButtonToValue(double value) {
        if (value < 0. || value > 1.)   return;

        this.value = value;
        buttonX = (int) (width * value) - buttonW + this.x;
    }


    /**
     * Returns if a point is on the slider's button.
     * @param x X coords of the point.
     * @param y Y coords of the point.
     * @return true if the point is on the button, else false.
     */
    public boolean isOnButton(int x, int y) {
        if (x <= buttonX + buttonW && x >= buttonX) {
            return y >= buttonY && y < buttonY + buttonH;
        }

        return false;
    }


    /**
     * Returns the current value between 0 and 1.
     * @return Current value.
     */
    public double getValue() {
        return value;
    }
}

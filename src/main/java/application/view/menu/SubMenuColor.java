package application.view.menu;

import application.utils.Color;
import application.view.IConcreteView;
import application.view.ViewBridge;
import application.view.element.Slider;


/**
 * Represent the color submenu of edition menu.
 */
public class SubMenuColor extends ViewBridge implements EditionSubMenu {
    private int x, y, width, height;

    // Value of the button on the progress bars.
    private double Rvalue, Gvalue, Bvalue;
    private Color color;

    Slider Rslider, Gslider, Bslider;


    /**
     * Parameterized constructor.
     * @param view Implementation to use for drawing.
     * @param x Top left x coords.
     * @param y Top left y coords.
     * @param width Width of the submenu.
     * @param height Height of the submenu.
     */
    public SubMenuColor(IConcreteView view, int x, int y, int width, int height) {
        super(view);

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        color = new Color(0, 0, 0);

        Rvalue = 0.5;
        Gvalue = 0.5;
        Bvalue = 0.5;


        Rslider = new Slider(implementation, width/5, height / 8, (int) (width * 0.7), 2, 0.5, 8, 18);
        Gslider = new Slider(implementation, width/5, 2 * height / 8, (int) (width * 0.7), 2, 0.5, 8, 18);
        Bslider = new Slider(implementation, width/5, 3 * height / 8, (int) (width * 0.7), 2, 0.5, 8, 18);
    }


    /**
     * Updates color value according to slider's values.
     */
    private void updateColor() {
        color = new Color(
                (int) (255 * Rvalue),
                (int) (255 * Gvalue),
                (int) (255 * Bvalue)
        );
    }


    @Override
    public void draw(int x, int y) {
        this.x = x;
        this.y = y;

        drawRoundedRectShadow(x, y, width, height, 20, 2, Color.WHITE);

        drawText("R", x + 5, y + height/8, 15, Color.BLACK);
        drawText("G", x + 5, y + 2 * height/8, 15, Color.BLACK);
        drawText("B", x + 5, y + 3 * height/8, 15, Color.BLACK);

        Rslider.draw(x, y);
        Gslider.draw(x, y);
        Bslider.draw(x, y);

        updateColor();

        drawRoundedRectShadow(x + (int) (width/1.5), y + 5 * height/8, 50, 50, 50, 2, color);
    }


    /**
     * Returns the slider id for the given points if it's on button.
     * @param x X coords of the point.
     * @param y Y coords of the point.
     * @return Index of the slider if it's on button, else -1.
     */
    public int getSliderId(int x, int y) {
        if      (Rslider.isOnButton(x - this.x, y - this.y))     return 0;
        else if (Gslider.isOnButton(x - this.x, y - this.y))     return 1;
        else if (Bslider.isOnButton(x - this.x, y - this.y))     return 2;

        return -1;
    }


    /**
     * Moves the slider button to the position, and update color value.
     * @param x X coords of the new position.
     * @param y Y coords of the new position.
     * @param sliderId Index of the slider to move.
     */
    public void moveSlider(int x, int y, int sliderId) {
        if (sliderId == 0)  {
            Rslider.moveButton(x - this.x);
            Rvalue = Rslider.getValue();
        }

        else if (sliderId == 1)  {
            Gslider.moveButton(x - this.x);
            Gvalue = Gslider.getValue();
        }

        else if (sliderId == 2)  {
            Bslider.moveButton(x - this.x);
            Bvalue = Bslider.getValue();
        }

        draw(this.x, this.y);
    }


    /**
     * Moves the slider button to the given value.
     * @param value New value for the slider, must be between 0 and 1.
     * @param sliderId Index of the slider to move.
     */
    public void moveSlider(double value, int sliderId) {
        if (sliderId == 0)  {
            Rslider.moveButtonToValue(value);
            Rvalue = Rslider.getValue();
        }

        else if (sliderId == 1)  {
            Gslider.moveButtonToValue(value);
            Gvalue = Gslider.getValue();
        }

        else if (sliderId == 2)  {
            Bslider.moveButtonToValue(value);
            Bvalue = Bslider.getValue();
        }

        draw(this.x, this.y);
    }


    /**
     * Returns the current color corresponding to slider values.
     * @return Selected color.
     */
    public Color getColor() {
        return color;
    }


    @Override
    public String getName() {
        return "color";
    }
}

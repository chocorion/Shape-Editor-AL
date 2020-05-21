package application.view.menu;

import application.utils.Color;
import application.view.IConcreteView;
import application.view.ViewBridge;
import application.view.element.Slider;

public class SubMenuRotate extends ViewBridge implements EditionSubMenu {
    private int x, y, width, height;
    Slider slider;


    /**
     * Parameterized constructor.
     * @param view Implementation to use for drawing.
     * @param x Top left x coords.
     * @param y Top left y coords.
     * @param width Width of the submenu.
     * @param height Height of the submenu.
     */
    public SubMenuRotate(IConcreteView view, int x, int y, int width, int height) {
        super(view);

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        slider = new Slider(implementation, width/5, height / 8, (int) (width * 0.7), 2, 0.5, 8, 18);
    }


    @Override
    public void draw(int x, int y) {
        this.x = x;
        this.y = y;

        drawRoundedRectShadow(x, y, width, height, 20, 2, new Color(230,230,250));
        drawText("Rotation", x + 5, y + height/7, 35, Color.BLACK);

        slider.draw(x, y);
    }


    /**
     * Returns the slider id for the given points if it's on button.
     * @param x X coords of the point.
     * @param y Y coords of the point.
     * @return Index of the slider if it's on button, else -1.
     */
    public int getSliderId(int x, int y) {
        if (slider.isOnButton(x - this.x, y - this.y)) return 0;

        return -1;
    }


    /**
     * Return the current value, between 0 and 1, of the slider.
     * @param inputId Index of the slider.
     * @return Value of the selected slider if exists, else 0.
     */
    public double getValue(int inputId) {
        if (inputId == 0)
            return slider.getValue();

        return 0.;
    }


    /**
     * Moves the slider button to the position, and update rotation value.
     * @param x X coords of the new position.
     * @param y Y coords of the new position.
     * @param sliderId Index of the slider to move.
     */
    public void moveSlider(int x, int y, int sliderId) {
        if (sliderId == 0)  {
            slider .moveButton(x - this.x);
            draw(this.x, this.y);
        }
    }


    @Override
    public String getName() {
        return "Rotate";
    }
}

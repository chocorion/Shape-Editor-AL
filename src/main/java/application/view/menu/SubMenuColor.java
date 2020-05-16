package application.view.menu;

import application.utils.Color;
import application.view.ViewBridge;
import application.view.element.Slider;


public class SubMenuColor implements EditionSubMenu {
    private int x, y, width, height;
    private ViewBridge view;

    // Value of the button on the progress bars.
    private double Rvalue, Gvalue, Bvalue;
    private Color color;

    Slider Rslider, Gslider, Bslider;

    public SubMenuColor(ViewBridge view, int x, int y, int width, int height) {
        this.view = view;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        color = new Color(0, 0, 0);

        Rvalue = 0.5;
        Gvalue = 0.5;
        Bvalue = 0.5;


        Rslider = new Slider(view, width/5, height / 8, (int) (width * 0.7), 2, 0.5, 8, 18);
        Gslider = new Slider(view, width/5, 2 * height / 8, (int) (width * 0.7), 2, 0.5, 8, 18);
        Bslider = new Slider(view, width/5, 3 * height / 8, (int) (width * 0.7), 2, 0.5, 8, 18);
    }

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

        view.drawRoundedRectShadow(x, y, width, height, 20, 2, Color.WHITE);

        view.drawText("R", x + 5, y + height/8, 15, Color.BLACK);
        view.drawText("G", x + 5, y + 2 * height/8, 15, Color.BLACK);
        view.drawText("B", x + 5, y + 3 * height/8, 15, Color.BLACK);

        Rslider.draw(x, y);
        Gslider.draw(x, y);
        Bslider.draw(x, y);

        updateColor();

        view.drawRoundedRectShadow(x + (int) (width/1.5), y + 5 * height/8, 50, 50, 50, 2, color);
    }

    public int getSliderId(int x, int y) {
        if      (Rslider.isOnButton(x - this.x, y - this.y))     return 0;
        else if (Gslider.isOnButton(x - this.x, y - this.y))     return 1;
        else if (Bslider.isOnButton(x - this.x, y - this.y))     return 2;

        return -1;
    }

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

    public Color getColor() {
        return color;
    }

    @Override
    public String getName() {
        return "color";
    }
}

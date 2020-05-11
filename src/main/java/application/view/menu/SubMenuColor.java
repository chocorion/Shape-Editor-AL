package application.view.menu;

import application.model.shape.Rectangle;
import application.utils.Color;
import application.view.ViewBridge;

class ColorSlider {
    static int width, height;
    int x, y, value;
    int buttonX, buttonY, buttonW, buttonH;

    public ColorSlider(int x, int y, int value, int buttonW, int buttonH) {
        this.x = x;
        this.y = y;
        this.value = value;

        buttonX = x - buttonW/2 + (int) (width * (value/100.));
        buttonY = y - buttonH/2;
        this.buttonW = buttonW;
        this.buttonH = buttonH;
    }

    void draw(ViewBridge view, int offsetX, int offsetY) {
        view.drawRectangle(x + offsetX, y + offsetY, width, height, Color.BLACK);

        view.drawRoundedRect(
                offsetX + buttonX,
                offsetY + buttonY,
                buttonW,
                buttonH,
                5,
                Color.BLUE
        );
    }

    void updateValue(int x, int y) {
        if (x > width || x < 0) return;

        buttonX = x - buttonW/2 + (int) (width * (value/100.));
        value = (int) (width/(double)buttonX) * 100;
    }

    boolean isOnButton(int x, int y) {
        if (x >= this.x && x < this.x + width) {
            return y >= this.y && y < this.y + height;
        }

        return false;
    }
}


public class SubMenuColor implements EditionSubMenu {
    private int x, y, width, height;
    private ViewBridge view;

    // Value of the button on the progress bars.
    private int Rvalue, Gvalue, Bvalue;
    private Color color;

    ColorSlider Rslider, Gslider, Bslider;

    public SubMenuColor(ViewBridge view, int x, int y, int width, int height) {
        this.view = view;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        color = new Color(0, 0, 0);

        ColorSlider.width = (int) (width * 0.7);
        ColorSlider.height = 2;

        Rvalue = 50;
        Gvalue = 50;
        Bvalue = 50;


        Rslider = new ColorSlider(width / 5, height / 8, Rvalue, 8, 18);
        Gslider = new ColorSlider(width / 5, 2 * height / 8, Rvalue, 8, 18);
        Bslider = new ColorSlider(width / 5, 3 * height / 8, Rvalue, 8, 18);
    }

    private void updateColor() {
        color = new Color(
                (int) (255 * Rvalue/100.),
                (int) (255 * Gvalue/100.),
                (int) (255 * Bvalue/100.)
        );
    }

    @Override
    public void draw(int x, int y) {
        this.x = x;
        this.y = y;

        view.drawStrokeRectangle(x, y, width, height, Color.BLUE);

        view.drawText("R", x + 5, y + height/8, 15, Color.BLACK);
        view.drawText("G", x + 5, y + 2 * height/8, 15, Color.BLACK);
        view.drawText("B", x + 5, y + 3 * height/8, 15, Color.BLACK);

        Rslider.draw(view, x, y);
        Gslider.draw(view, x, y);
        Bslider.draw(view, x, y);

        updateColor();

        view.drawText("Current color:", x + 5, y + 5 * height/8, 90, Color.BLACK);
        view.drawRoundedRect(x + (int) (width/1.5), y + 5 * height/8, 50, 50, 50, color);
    }

    public int getSliderId(int x, int y) {
        if      (Rslider.isOnButton(x - this.x, y - this.y))     return 0;
        else if (Gslider.isOnButton(x - this.x, y - this.y))     return 1;
        else if (Bslider.isOnButton(x - this.x, y - this.y))     return 2;

        return -1;
    }

    public void moveSlider(int x, int y, int sliderId) {
        if (sliderId == 0)  {
            Rslider.updateValue(x - this.x, y - this.y);
            Rvalue = Rslider.value;
        }

        else if (sliderId == 1)  {
            Gslider.updateValue(x - this.x, y - this.y);
            Gvalue = Gslider.value;
        }

        else if (sliderId == 2)  {
            Bslider.updateValue(x - this.x, y - this.y);
            Bvalue = Bslider.value;
        }

        draw(this.x, this.y);
    }


    @Override
    public String getName() {
        return "color";
    }
}

package application.view.menu;

import application.utils.Color;
import application.view.ViewBridge;
import application.view.element.Slider;

public class SubMenuRotate implements EditionSubMenu {
    private int x, y, width, height;
    private ViewBridge view;

    Slider slider;

    public SubMenuRotate(ViewBridge view, int x, int y, int width, int height) {
        this.view = view;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        slider = new Slider(view, width/5, height / 8, (int) (width * 0.7), 2, 0.5, 8, 18);
    }

    @Override
    public void draw(int x, int y) {
        this.x = x;
        this.y = y;

        view.drawRoundedRectShadow(x, y, width, height, 20, 2, Color.WHITE);

        view.drawText("Rotation", x + 5, y + height/8, 30, Color.BLACK);

        slider.draw(x, y);
    }

    public int getSliderId(int x, int y) {
        if (slider.isOnButton(x - this.x, y - this.y)) return 0;

        return -1;
    }

    public double getValue(int inputId) {
        if (inputId == 0)
            return slider.getValue();

        return 0.;
    }

    public void moveSlider(double value, int sliderId) {
        if (sliderId == 0)  {
            slider.moveButtonToValue(value);
            draw(this.x, this.y);
        }
    }

    public void moveSlider(int x, int y, int sliderId) {
        if (sliderId == 0)  {
            slider .moveButton(x - this.x);
            draw(this.x, this.y);
        }
    }


    @Override
    public String getName() {
        return "rotate";
    }
}

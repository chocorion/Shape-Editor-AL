package application.view.menu;

import application.utils.Color;
import application.view.ViewBridge;
import application.view.element.Slider;

public class SubMenuResizeRectangle implements EditionSubMenu {
    private int x, y, width, height;
    private ViewBridge view;

    Slider SliderWidth, SliderHeight;

    public SubMenuResizeRectangle(ViewBridge view, int x, int y, int width, int height) {
        this.view = view;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        SliderWidth = new Slider(view, width/5, height / 8, (int) (width * 0.7), 2, 0.5, 8, 18);
        SliderHeight = new Slider(view, width/5, 2 * height / 8, (int) (width * 0.7), 2, 0.5, 8, 18);
    }

    @Override
    public void draw(int x, int y) {
        this.x = x;
        this.y = y;

        view.drawRoundedRectShadow(x, y, width, height, 20, 2, Color.WHITE);

        view.drawText("Width", x + 5, y + height/8, 30, Color.BLACK);
        view.drawText("Height", x + 5, y + 2 * height/8, 30, Color.BLACK);

        SliderWidth.draw(x, y);
        SliderHeight.draw(x, y);
    }

    public int getSliderId(int x, int y) {
        if      (SliderWidth.isOnButton(x - this.x, y - this.y))     return 0;
        else if (SliderHeight.isOnButton(x - this.x, y - this.y))     return 1;

        return -1;
    }

    public void moveSlider(int x, int y, int sliderId) {
        if (sliderId == 0)  {
            SliderWidth.moveButton(x - this.x);
            //Rvalue = Rslider.getValue();
        }

        else if (sliderId == 1)  {
            SliderHeight.moveButton(x - this.x);
            //Gvalue = Gslider.getValue();
        }


        draw(this.x, this.y);
    }

    /*public void moveSlider(double value, int sliderId) {
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
    }*/

    @Override
    public String getName() {
        return "resize";
    }
}

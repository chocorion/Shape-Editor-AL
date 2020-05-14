package application.view.element;

import application.utils.Color;
import application.view.ViewBridge;

public class Slider {
    private int width, height;
    private int x, y;
    private int buttonX, buttonY, buttonW, buttonH;

    private double value;
    private ViewBridge view;


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

    public void moveButton(int x) {
        if (x < this.x)
            buttonX = this.x - buttonW/2;

        else if (x > this.x + width)
            buttonX = this.x + width - buttonW/2;

        else
            buttonX = x - buttonW/2;


        value = ((buttonX + buttonW/2 - this.x))/(double)width;
    }
    

    public void moveButtonToValue(double value) {
        if (value < 0. || value > 1.)   return;

        this.value = value;
        buttonX = (int) (width * value) - buttonW + this.x;
    }


    public boolean isOnButton(int x, int y) {
        if (x <= buttonX + buttonW && x >= buttonX) {
            return y >= buttonY && y < buttonY + buttonH;
        }

        return false;
    }
    
    public double getValue() {
        return value;
    }
}

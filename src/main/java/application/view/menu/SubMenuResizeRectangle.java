package application.view.menu;

import application.utils.Color;
import application.view.ViewBridge;
import application.view.element.TextInput;

public class SubMenuResizeRectangle implements EditionSubMenu {
    private int x, y, width, height;
    private ViewBridge view;

    TextInput widthInput, heightInput;

    public SubMenuResizeRectangle(ViewBridge view, int x, int y, int width, int height) {
        this.view = view;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        widthInput = new TextInput(view, width/5, height / 8, (int) (width * 0.7), 18);
        heightInput = new TextInput(view, width/5, 2 * height / 8, (int) (width * 0.7), 18);
    }

    @Override
    public void draw(int x, int y) {
        this.x = x;
        this.y = y;

        view.drawRoundedRectShadow(x, y, width, height, 20, 2, Color.WHITE);

        view.drawText("Width", x + 5, y + height/8, 30, Color.BLACK);
        view.drawText("Height", x + 5, y + 2 * height/8, 30, Color.BLACK);

        widthInput.draw(x, y);
        heightInput.draw(x, y);
    }

    public int getInputId(int x, int y) {
        if      (widthInput.isIn(x - this.x, y - this.y))     return 0;
        else if (heightInput.isIn(x - this.x, y - this.y))     return 1;

        return -1;
    }

    public String getText(int inputId) {
        if (inputId == 0)
            return widthInput.getText();

        else if (inputId == 1)
            return heightInput.getText();

        return "";
    }

    public void addText(int inputId, String txt) {
        if (inputId == 0) {
            widthInput.append(txt);
        } else if (inputId == 1) {
            heightInput.append(txt);
        }
    }

    @Override
    public String getName() {
        return "resize";
    }
}

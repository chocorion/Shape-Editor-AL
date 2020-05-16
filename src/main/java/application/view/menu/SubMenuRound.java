package application.view.menu;

import application.utils.Color;
import application.view.ViewBridge;
import application.view.element.TextInput;
import application.view.element.interaction.Interaction;

public class SubMenuRound implements EditionSubMenu {
    private int x, y, width, height;
    private ViewBridge view;

    TextInput sizeInput;

    public SubMenuRound(ViewBridge view, int x, int y, int width, int height) {
        this.view = view;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        sizeInput = new TextInput(view, width/5, height / 8, (int) (width * 0.7), 18);
    }

    @Override
    public void draw(int x, int y) {
        this.x = x;
        this.y = y;

        view.drawRoundedRectShadow(x, y, width, height, 20, 2, Color.WHITE);

        view.drawText("Round", x + 5, y + height/8, 30, Color.BLACK);

        sizeInput.draw(x, y);
    }

    public int getInputId(int x, int y) {
        if (sizeInput.isIn(x - this.x, y - this.y)) return 0;

        return -1;
    }

    public Interaction getInteraction(int inputId) {
        if (inputId == 0)
            return sizeInput.getInteraction();

        return null;
    }

    public String getText(int inputId) {
        if (inputId == 0)
            return sizeInput.getText();

        return "";
    }


    @Override
    public String getName() {
        return "round";
    }
}

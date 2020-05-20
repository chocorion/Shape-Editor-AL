package application.view.menu;

import application.utils.Color;
import application.view.IConcreteView;
import application.view.ViewBridge;
import application.view.element.TextInput;
import application.view.element.interaction.Interaction;

public class SubMenuResizeRectangle extends ViewBridge implements EditionSubMenu {
    private int x, y, width, height;
    TextInput widthInput, heightInput;

    /**
     * Parameterized constructor.
     * @param view Implementation to use for drawing.
     * @param x Top left x coords.
     * @param y Top left y coords.
     * @param width Width of the submenu.
     * @param height Height of the submenu.
     */
    public SubMenuResizeRectangle(IConcreteView view, int x, int y, int width, int height) {
        super(view);

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        widthInput = new TextInput(this, width/5, height / 8, (int) (width * 0.7), 18);
        heightInput = new TextInput(this, width/5, 2 * height / 8, (int) (width * 0.7), 18);
    }


    @Override
    public void draw(int x, int y) {
        this.x = x;
        this.y = y;

        drawRoundedRectShadow(x, y, width, height, 20, 2, Color.WHITE);

        drawText("Width", x + 5, y + height/8, 30, Color.BLACK);
        drawText("Height", x + 5, y + 2 * height/8, 30, Color.BLACK);

        widthInput.draw(x, y);
        heightInput.draw(x, y);
    }


    /**
     * Returns the input id for the given points if it's on input.
     * @param x X coords of the point.
     * @param y Y coords of the point.
     * @return Index of the input if it's on it, else -1.
     */
    public int getInputId(int x, int y) {
        if      (widthInput.isIn(x - this.x, y - this.y))     return 0;
        else if (heightInput.isIn(x - this.x, y - this.y))     return 1;

        return -1;
    }


    /**
     * Returns the interaction available for the given input.
     * @param inputId Index of the input.
     * @return Available interaction if input exists, else null.
     */
    public Interaction getInteraction(int inputId) {
        if (inputId == 0)
            return widthInput.getInteraction();

        if (inputId == 1)
            return heightInput.getInteraction();

        return null;
    }


    /**
     * Returns the text of the given input.
     * @param inputId Index of the input.
     * @return Text if input exists, else null.
     */
    public String getText(int inputId) {
        if (inputId == 0)
            return widthInput.getText();

        else if (inputId == 1)
            return heightInput.getText();

        return "";
    }


    @Override
    public String getName() {
        return "resize";
    }
}

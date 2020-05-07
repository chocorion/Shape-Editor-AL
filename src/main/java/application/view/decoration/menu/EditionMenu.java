package application.view.decoration.menu;

import application.model.shape.Rectangle;
import application.view.ObserverDecoration;

public class EditionMenu extends Menu {
    private static int width = 175;
    private static int height = 175;

    public EditionMenu(ObserverDecoration decoration) {
        super(decoration);
    }

    @Override
    public void draw() {
        super.draw();

        super.drawRectangle(
                new Rectangle(this.x, this.y, width, height)
        );

        System.out.println("DRAWING EDITION MENU");
    }

    @Override
    public void update() {
        this.draw();
    }

    public boolean isIn(int x, int y) {
        return (x <= this.x + width && x >= this.x && y <= this.y + height && y >= this.y);
    }
}

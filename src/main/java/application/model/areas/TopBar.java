package application.model.areas;

import application.model.Model;
import application.utils.ModelObservableImp;

public class TopBar extends ModelObservableImp {
    private Model model;

    private int x, y;
    private int width, height;

    private int buttonSize;

    public TopBar(Model model) {
        this.model = model;

        // Margin of 1 around the bar
        this.x = 1;
        this.y = 1;

        this.width = model.getWidth() - 2;
        this.height = 30;

        this.buttonSize = this.height - y - 6;
    }

    public void update() {
        super.notifyObserver();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getButtonWidth() {
        return this.buttonSize;
    }

    public boolean isIn(int x, int y) {
        return (y >= this.y && y <= this.height + this.y) && (x >= 1 && x <= this.x + this.width);
    }

    public void clickOnButton(int x, int y) {
        if (!this.isIn(x, y)) {
            return;
        }

        int marge = 4;

        if (x >= marge && x <= marge + this.getButtonWidth()) {
            System.out.println("UNDO");
            this.model.undo();
        } else if (x >= this.getButtonWidth() + 2 * marge && x <= 2 * this.getButtonWidth() + 2 * marge) {
            System.out.println("REDO");
            this.model.redo();
        }
    }
}

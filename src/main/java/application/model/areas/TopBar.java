package application.model.areas;

import application.model.Model;
import application.utils.ModelObservableImp;

public class TopBar extends ModelObservableImp {
    private Model model;

    public TopBar(Model model) {
        this.model = model;
    }

    public void update() {
        super.notifyObserver();
    }

    public int getWidth() {
        return this.model.getWidth();
    }

    public int getHeight() {
        return 30;
    }

    public int getButtonWidth() {
        return 22;
    }

    public boolean isIn(int x, int y) {
        return (y >= 0 && y <= this.getHeight());
    }

    public void clickOnButton(int x, int y) {
        if (!this.isIn(x, y)) {
            return;
        }

        if (x >= 4 && x <= 4 + this.getButtonWidth()) {
            System.out.println("UNDO");
            this.model.undo();
        } else if (x >= this.getButtonWidth() + 6 && x <= this.getButtonWidth() + 6 + this.getButtonWidth()) {
            System.out.println("REDO");
            this.model.redo();
        }
    }
}

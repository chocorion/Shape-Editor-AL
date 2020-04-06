package application.view;

import application.model.shape.Rectangle;
import application.utils.ModelObserver;

public class ToolBar extends ViewDecorator implements ModelObserver {
    public ToolBar(View view) {
        super(view);
    }

    @Override
    public void draw() {
        super.draw();
        // Draw toolbar

        super.drawRectangle(new Rectangle(0, 0, 30, super.getHeight()));
    }

    @Override
    public void update() {
        this.draw();
    }
}

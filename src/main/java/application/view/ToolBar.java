package application.view;

import application.utils.ModelObserver;

public class ToolBar extends ViewDecorator implements ModelObserver {
    public ToolBar(View view) {
        super(view);
    }

    @Override
    public void draw() {
        super.draw();

        // Draw toolbar
    }

    @Override
    public void update() {
        this.draw();
    }
}

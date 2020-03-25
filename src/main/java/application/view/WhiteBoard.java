package application.view;

import application.utils.ModelObserver;

public class WhiteBoard extends ViewDecorator implements ModelObserver {
    public WhiteBoard(View view) {
        super(view);
    }

    @Override
    public void draw() {
        super.draw();

        // Draw big white rectangle
    }

    @Override
    public void update() {
        this.draw();
    }
}
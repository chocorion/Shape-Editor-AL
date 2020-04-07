package application.view;

import application.model.areas.WhiteBoard;
import application.utils.ModelObserver;

public class WhiteBoardView extends ViewDecorator implements ModelObserver {
    private WhiteBoard whiteBoard;

    public WhiteBoardView(View view, WhiteBoard whiteBoard) {
        super(view);

        this.whiteBoard = whiteBoard;
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
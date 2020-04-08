package application.view;

import application.model.Model;
import application.model.areas.WhiteBoard;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.ModelObserver;
import application.utils.Color;

public class WhiteBoardView extends ViewDecorator implements ModelObserver {
    private WhiteBoard whiteBoard;

    public WhiteBoardView(View view, WhiteBoard whiteBoard) {
        super(view);

        this.whiteBoard = whiteBoard;
    }

    @Override
    public void draw() {
        super.draw();

        int borderSize = 1;

        super.drawRectangle(new Rectangle(whiteBoard.getX(), whiteBoard.getY(), whiteBoard.getWidth(), whiteBoard.getHeight(), Color.BLACK));
        super.drawRectangle(new Rectangle(whiteBoard.getX() + borderSize, whiteBoard.getY() + borderSize, whiteBoard.getWidth() - 2 * borderSize, whiteBoard.getHeight() - 2 * borderSize, Color.WHITE));

        // Draw big white rectangle
        for (Shape shape:whiteBoard.getInnerShapes()) {
            shape.draw(this);
        }
    }

    @Override
    public void update() {
        this.draw();
    }
}
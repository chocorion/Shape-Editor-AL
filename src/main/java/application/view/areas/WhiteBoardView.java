package application.view.areas;

import application.model.areas.WhiteBoard;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.ModelObservable;
import application.utils.ModelObserver;
import application.utils.Color;
import application.view.ObserverDecoration;
import application.view.View;
import application.view.ViewDecorator;

public class WhiteBoardView extends ViewDecorator implements ObserverDecoration {
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

    @Override
    public ModelObservable getSubject() {
        return this.whiteBoard;
    }
}
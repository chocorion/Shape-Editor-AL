package application.view.areas;

import application.model.Model;
import application.model.areas.ToolBar;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.Color;
import application.utils.ModelObserver;
import application.view.View;
import application.view.ViewDecorator;

public class ToolBarView extends ViewDecorator implements ModelObserver {
    private ToolBar toolBar;

    public ToolBarView(View view, ToolBar toolBar) {
        super(view);

        this.toolBar = toolBar;
    }

    @Override
    public void draw() {
        super.draw();
        // Draw toolbar
        int borderSize = 1;

        super.drawRectangle(
                new Rectangle(
                        toolBar.getX(),
                        toolBar.getY(),
                        toolBar.getWidth(),
                        toolBar.getHeight(),
                        Color.BLACK
                )
        );

        super.drawRectangle(
                new Rectangle(
                        toolBar.getX() + borderSize,
                        toolBar.getY() + borderSize,
                        toolBar.getWidth() - 2 * borderSize,
                        toolBar.getHeight() - 2 * borderSize,
                        Color.WHITE
                )
        );

        double innerShapeMaxSize = Math.min(toolBar.getWidth() - 6 * borderSize, toolBar.getHeight() - 6 * borderSize);
        int index = 0;
        int marginBetweenCase = 10;

        for (Shape shape:toolBar.getInnerShapes()) {
            double factor = innerShapeMaxSize/Math.max(shape.getWidth(), shape.getHeight());
            Shape minimizedShape = (Shape) shape.clone();

            minimizedShape.resize(factor);
            minimizedShape.moveTo(
                    toolBar.getX() + 3 * borderSize,
                    toolBar.getY() + index * ((int) innerShapeMaxSize + marginBetweenCase) + 4 * borderSize
            );

            minimizedShape.draw(this);
            index++;
        }
    }

    @Override
    public void update() {
        this.draw();
    }
}

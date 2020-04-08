package application.view;

import application.model.Model;
import application.model.areas.ToolBar;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.Color;
import application.utils.ModelObserver;

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

        for (Shape shape:toolBar.getInnerShapes()) {
            shape.draw(this);
        }
    }

    @Override
    public void update() {
        this.draw();
    }
}

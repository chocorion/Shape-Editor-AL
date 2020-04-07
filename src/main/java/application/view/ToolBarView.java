package application.view;

import application.model.areas.ToolBar;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
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

        super.drawRectangle(new Rectangle(0, 0, 30, super.getHeight()));

        for (Shape shape:this.toolBar.getInnerShapes()) {
            shape.draw(this);
        }
    }

    @Override
    public void update() {
        this.draw();
    }
}

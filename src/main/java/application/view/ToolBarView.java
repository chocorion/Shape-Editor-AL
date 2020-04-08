package application.view;

import application.model.Model;
import application.model.areas.ToolBar;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.Color;
import application.utils.ModelObserver;

public class ToolBarView extends ViewDecorator implements ModelObserver {
    private Model model;

    public ToolBarView(View view, Model model) {
        super(view);

        this.model = model;
    }

    @Override
    public void draw() {
        super.draw();
        // Draw toolbar

        super.drawRectangle(
                new Rectangle(
                        1,
                        model.getTopBar().getHeight() + 2,
                        model.getToolBar().getWidth() - 2,
                        model.getToolBar().getHeight() - 3,
                        Color.BLACK
                )
        );

        super.drawRectangle(
                new Rectangle(
                        2,
                        model.getTopBar().getHeight() + 3,
                        model.getToolBar().getWidth() - 4,
                        model.getToolBar().getHeight() - 5,
                        Color.WHITE
                )
        );

        for (Shape shape:model.getToolBar().getInnerShapes()) {
            shape.draw(this);
        }
    }

    @Override
    public void update() {
        this.draw();
    }
}

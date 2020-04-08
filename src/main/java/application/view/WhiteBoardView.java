package application.view;

import application.model.Model;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.ModelObserver;
import application.utils.Color;

public class WhiteBoardView extends ViewDecorator implements ModelObserver {
    private Model model;

    public WhiteBoardView(View view, Model model) {
        super(view);

        this.model = model;
    }

    @Override
    public void draw() {
        super.draw();

        int margeX = model.getToolBar().getWidth() + 2;
        int margeY = model.getTopBar().getHeight() + 2;
        super.drawRectangle(new Rectangle(margeX, margeY, model.getWidth() - margeX, model.getHeight() - margeY, Color.WHITE));

        // Draw big white rectangle
        for (Shape shape:this.model.getWhiteBoard().getInnerShapes()) {
            shape.draw(this);
        }
    }

    @Override
    public void update() {
        this.draw();
    }
}
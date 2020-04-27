package application.view.areas;

import application.model.areas.ToolBar;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.Color;
import application.utils.ModelObservable;
import application.utils.ModelObserver;
import application.view.ObserverDecoration;
import application.view.View;
import application.view.ViewDecorator;

import java.util.HashMap;

public class ToolBarView extends ViewDecorator implements ObserverDecoration {
    private ToolBar toolBar;
    private final HashMap<Shape, Shape> minimisedShapes;

    private static int BORDER_SIZE = 1;
    private static int CASE_SIZE;
    private static int CASE_MARGIN = 10;

    public ToolBarView(View view, ToolBar toolBar) {
        super(view);

        this.toolBar = toolBar;
        this.minimisedShapes = new HashMap<>();

        CASE_SIZE = Math.min(toolBar.getWidth() - 6 * BORDER_SIZE, toolBar.getHeight() - 6 * BORDER_SIZE);
    }

    @Override
    public void draw() {
        super.draw();
        // Draw toolbar

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
                        toolBar.getX() + BORDER_SIZE,
                        toolBar.getY() + BORDER_SIZE,
                        toolBar.getWidth() - 2 * BORDER_SIZE,
                        toolBar.getHeight() - 2 * BORDER_SIZE,
                        Color.WHITE
                )
        );

        
        int index = 0;

        for (Shape shape:toolBar.getInnerShapes()) {
            Shape minimizedShape = minimisedShapes.get(shape);

            minimizedShape.moveTo(
                    toolBar.getX() + 3 * BORDER_SIZE,
                    toolBar.getY() + index * (CASE_SIZE + CASE_MARGIN) + 4 * BORDER_SIZE
            );

            minimizedShape.draw(this);
            index++;
        }
    }

    @Override
    public void update() {
        for (Shape shape : toolBar.getInnerShapes()) {
            if (!minimisedShapes.containsKey(shape)) {
                minimisedShapes.put(shape, getMinimisedClone(shape));
            }
        }
        
        this.draw();
    }

    private Shape getMinimisedClone(Shape shape) {
        Shape minimised = (Shape) shape.clone();

        double innerShapeMaxSize = Math.min(toolBar.getWidth() - 6 * BORDER_SIZE, toolBar.getHeight() - 6 * BORDER_SIZE);
        double factor = innerShapeMaxSize/Math.max(shape.getWidth(), shape.getHeight());

        minimised.resize(factor);

        return minimised;
    }

    public int getShapeId(int x, int y) {
        if (x > toolBar.getX() + 3 * BORDER_SIZE + toolBar.getWidth()) {
            return -1;
        }

        return (y - toolBar.getY() - 4 * BORDER_SIZE)/(CASE_SIZE + CASE_MARGIN);
    }

    @Override
    public ModelObservable getSubject() {
        return this.toolBar;
    }
}

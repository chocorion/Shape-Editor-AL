package application.view.areas;

import application.model.areas.ToolBar;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.Color;
import application.utils.ImageManager;
import application.utils.ModelObservable;
import application.utils.ModelObserver;
import application.view.*;

import java.util.HashMap;

public class ToolBarView implements ModelObserver {
    private ToolBar toolBar;
    private final HashMap<Shape, Shape> minimisedShapes;

    private static int CASE_MARGIN = 5;
    private static int START_MARGIN = 5;

    private Rectangle area;
    private Rectangle trash;
    private int caseSize;

    private ViewBridge view;

    public ToolBarView(ViewBridge view, ToolBar toolBar) {
        this.view = view;

        this.toolBar = toolBar;
        this.minimisedShapes = new HashMap<>();

        area = Layout.getToolBar();
        trash = new Rectangle(
                area.getX() + CASE_MARGIN,
                area.getHeight() - Math.min(area.getWidth(), area.getHeight()) + CASE_MARGIN,
                Math.min(area.getWidth(), area.getHeight()) - 2 * CASE_MARGIN,
                Math.min(area.getWidth(), area.getHeight()) - 2 * CASE_MARGIN
        );

        caseSize = (int) Math.min(
                area.getWidth() - 2 * CASE_MARGIN,
                area.getHeight() - 2 * CASE_MARGIN
        );
    }

    public void draw() {
        // Draw toolbar

        view.drawRectangle(area);

        view.drawRectangle(
                new Rectangle(
                        area.getX() + Layout.BORDER,
                        area.getY() + Layout.BORDER,
                        area.getWidth() - 2 * Layout.BORDER,
                        area.getHeight() - 2 * Layout.BORDER,
                        Color.WHITE
                )
        );

        
        int index = 0;

        for (Shape shape:toolBar.getInnerShapes()) {
            Shape minimizedShape = getMinimisedClone(shape);

            minimizedShape.moveTo(
                    area.getX() + CASE_MARGIN,
                    area.getY() + index * (CASE_MARGIN + caseSize)+ START_MARGIN
            );

            minimizedShape.draw(view);
            index++;
        }


        view.drawImage(ImageManager.getImage("trash"), trash);
    }

    @Override
    public void update() {
        area = Layout.getToolBar();
        caseSize = (int) Math.min(
                area.getWidth() - 2 * CASE_MARGIN,
                area.getHeight() - 2 * CASE_MARGIN
        );

        trash = new Rectangle(
                area.getX() + CASE_MARGIN,
                area.getHeight() - Math.min(area.getWidth(), area.getHeight()) + CASE_MARGIN,
                Math.min(area.getWidth(), area.getHeight()) - 2 * CASE_MARGIN,
                Math.min(area.getWidth(), area.getHeight()) - 2 * CASE_MARGIN
        );
        
        this.draw();
    }

    private Shape getMinimisedClone(Shape shape) {
        Shape minimised = (Shape) shape.clone();

        double innerShapeMaxSize = caseSize;
        double factor = innerShapeMaxSize/Math.max(shape.getWidth(), shape.getHeight());

        minimised.resize(factor);

        return minimised;
    }

    public boolean isInTrash(int x, int y) {
        return trash.isIn(x, y);
    }

    public int getShapeId(int x, int y) {
        if (x > area.getX() + area.getWidth() || x < area.getX()) {
            return -1;
        }

        return (int) ((y - area.getY())/(caseSize + CASE_MARGIN));
    }
}

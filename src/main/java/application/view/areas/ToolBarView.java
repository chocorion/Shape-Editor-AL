package application.view.areas;

import application.model.areas.ToolBar;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.Color;
import application.utils.ImageManager;
import application.utils.ModelObservable;
import application.utils.ModelObserver;
import application.view.MainView;
import application.view.ObserverDecoration;
import application.view.View;
import application.view.ViewDecorator;

import java.util.HashMap;

public class ToolBarView extends ViewDecorator implements ObserverDecoration {
    private ToolBar toolBar;
    private final HashMap<Shape, Shape> minimisedShapes;

    private static int CASE_MARGIN = 5;

    private Rectangle area;
    private Rectangle trash;
    private int caseSize;

    public ToolBarView(MainView mainView, View view, ToolBar toolBar) {
        super(view);

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

    @Override
    public void draw() {
        super.draw();
        // Draw toolbar

        super.drawRectangle(area);

        super.drawRectangle(
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
                    area.getY() + index * (CASE_MARGIN + caseSize)
            );

            minimizedShape.draw(this);
            index++;
        }


        super.drawImage(ImageManager.getImage("trash"), trash);
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

    @Override
    public ModelObservable getSubject() {
        return this.toolBar;
    }
}

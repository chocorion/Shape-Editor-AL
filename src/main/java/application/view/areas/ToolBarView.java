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

/**
 * Represent the view of the tool bar.
 */
public class ToolBarView implements ModelObserver {
    private ToolBar toolBar;
    private final HashMap<Shape, Shape> minimisedShapes;

    private static int CASE_MARGIN = 5;
    private static int START_MARGIN = 5;

    private Rectangle area;
    private Rectangle trash;
    private int caseSize;

    private ViewBridge view;

    /**
     * Parameterized constructor.
     * @param view Bridge to the view implementation.
     * @param toolBar Model of the tool bar.
     */
    public ToolBarView(ViewBridge view, ToolBar toolBar) {
        this.view = view;

        this.toolBar = toolBar;
        this.minimisedShapes = new HashMap<>();

        area = Layout.getToolBar();
        trash = new Rectangle(
                area.getMinX() + CASE_MARGIN,
                area.getHeight() - Math.min(area.getWidth(), area.getHeight()) + CASE_MARGIN,
                Math.min(area.getWidth(), area.getHeight()) - 2 * CASE_MARGIN,
                Math.min(area.getWidth(), area.getHeight()) - 2 * CASE_MARGIN
        );

        caseSize = (int) Math.min(
                area.getWidth() - 2 * CASE_MARGIN,
                area.getHeight() - 2 * CASE_MARGIN
        );
    }


    /**
     * Draw the toolbar.
     */
    public void draw() {
        view.drawRectangle(area);

        view.drawRectangle(
                new Rectangle(
                        area.getMinX() + Layout.BORDER,
                        area.getMinY() + Layout.BORDER,
                        area.getWidth() - 2 * Layout.BORDER,
                        area.getHeight() - 2 * Layout.BORDER,
                        Color.WHITE
                )
        );

        
        int index = 0;

        for (Shape shape:toolBar.getInnerShapes()) {
            Shape minimizedShape = getMinimisedClone(shape);

            minimizedShape.moveTo(
                    area.getMinX() + CASE_MARGIN,
                    area.getMinY() + index * (CASE_MARGIN + caseSize)+ START_MARGIN
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
                area.getMinX() + CASE_MARGIN,
                area.getHeight() - Math.min(area.getWidth(), area.getHeight()) + CASE_MARGIN,
                Math.min(area.getWidth(), area.getHeight()) - 2 * CASE_MARGIN,
                Math.min(area.getWidth(), area.getHeight()) - 2 * CASE_MARGIN
        );
        
        this.draw();
    }


    /**
     * Creates a smaller version of the given shape, to fit the tool bar size.
     * @param shape The shape to minimize.
     * @return Minimised clone of the shape.
     */
    private Shape getMinimisedClone(Shape shape) {
        Shape minimised = (Shape) shape.clone();

        double innerShapeMaxSize = caseSize;
        double factor = innerShapeMaxSize/Math.max(shape.getWidth(), shape.getHeight());

        minimised.resize(factor);

        return minimised;
    }


    /**
     * Allows to know if a point is in the toolbar's trash.
     * @param x X coords of the point.
     * @param y Y coored of the point.
     * @return True if the point it in the trash, else false.
     */
    public boolean isInTrash(int x, int y) {
        return trash.isIn(x, y);
    }


    /**
     * Return the corresponding shape index for a point.
     * @param x
     * @param y
     * @return
     */
    public int getShapeId(int x, int y) {
        if (x > area.getMinX() + area.getWidth() || x < area.getMinX()) {
            return -1;
        }

        return (int) ((y - area.getMinY())/(caseSize + CASE_MARGIN));
    }
}

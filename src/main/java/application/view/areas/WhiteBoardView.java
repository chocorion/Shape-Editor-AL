package application.view.areas;

import application.model.areas.WhiteBoard;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.Color;
import application.utils.ModelObserver;
import application.view.*;

import application.view.menu.EditionMenu;
import application.view.menu.WhiteBoardMenu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represent the view of the whiteboard.
 */
public class WhiteBoardView extends ViewBridge implements ModelObserver {
    private final WhiteBoard whiteBoard;
    private final WhiteBoardMenu menu;
    private EditionMenu editionMenu;

    private final HashSet<Shape> selectedShapes;
    private Rectangle area;

    private boolean isMenuOpen;
    private boolean isEditionMenuOpen;
    private int menuX, menuY;


    /**
     * Parameterized constructor.
     * @param view Bridge to the view to use.
     * @param whiteBoard Model representation of the whiteboard.
     */
    public WhiteBoardView(IConcreteView view, WhiteBoard whiteBoard) {
        super(view);

        area = Layout.getWhiteBoard();

        this.whiteBoard = whiteBoard;
        menu = new WhiteBoardMenu(view);

        selectedShapes = new HashSet<>();
        isMenuOpen = false;
        isEditionMenuOpen = false;
    }


    /**
     * Draw the whiteboard.
     */
    public void draw() {
        drawRectangle(area);

        drawRectangle(
                new Rectangle(
                        area.getMinX() + Layout.BORDER,
                        area.getMinY() + Layout.BORDER,
                        area.getWidth() - 2 * Layout.BORDER,
                        area.getHeight() - 2 * Layout.BORDER,
                        Color.WHITE
                )
        );

        drawSelectedShapes(selectedShapes);

        // Draw big white rectangle
        for (Shape shape:whiteBoard.getInnerShapes()) {
            shape.draw(this);
        }


        if (isMenuOpen)
            menu.draw(menuX, menuY);
        else if (isEditionMenuOpen)
            editionMenu.draw(menuX, menuY);
    }


    /**
     * Adds shapes to the current selection.
     * @param shapes List of shape to add
     */
    public void addSelection(List<Shape> shapes) {
        selectedShapes.addAll(shapes);
    }

    /**
     * Adds shapes to the current selection.
     * @param shapes Set of shape to add
     */
    public void addSelection(Set<Shape> shapes) {
        selectedShapes.addAll(shapes);
    }


    /**
     * Returns all the currently selected shapes.
     * @return Set of selected shapes.
     */
    public HashSet<Shape> getSelectedShapes() {
        return selectedShapes;
    }


    /**
     * Clears the current selection.
     */
    public void clearSelection() {
        selectedShapes.clear();
    }

    public void drawSelectedShapes(HashSet<Shape> shapes) {
        for (Shape shape : shapes) {
            drawSelectedShapes(shape);
        }
    }


    /**
     * Draw given shape as selected.
     * @param shape Shape to draw as selected.
     */
    public void drawSelectedShapes(Shape shape) {
        int selectionMarge = 4;

        new Rectangle(
                shape.getMinX() - selectionMarge,
                shape.getMinY() - selectionMarge,
                shape.getMaxX() - shape.getMinX() + 2 * selectionMarge,
                shape.getMaxY() - shape.getMinY() + 2 * selectionMarge,
                Color.SELECTION
        ).draw(this);
    }


    @Override
    public void update() {
        area = Layout.getWhiteBoard();
        this.draw();
    }


    /**
     * Opens the whiteboard right click menu.
     * @param x X coords of the menu.
     * @param y Y coords of the menu.
     */
    public void openWhiteboardMenu(int x, int y) {
        menuX = x;
        menuY = y;

        isMenuOpen = true;
        draw();
    }


    /**
     * Closes the right click menu.
     */
    public void closeWhiteboardMenu() {
        isMenuOpen = false;
        draw();
    }


    /**
     * Opens edition menu for the given shape.
     * @param x X coords of the edition menu.
     * @param y Y coords of the edition menu.
     * @param selectedShape Shape to open the menu for.
     */
    public void openEditionMenu(int x, int y, Shape selectedShape) {
        menuX = x;
        menuY = y;

        isMenuOpen = false;
        isEditionMenuOpen = true;
        editionMenu = EditionMenu.getInstanceFor(implementation, selectedShape);
        draw();
    }


    /**
     * Close the edition menu, and re-open right click menu.
     */
    public void closeEditionMenu() {
        isEditionMenuOpen = false;
        isMenuOpen = true;
        editionMenu = null;

        draw();
    }


    /**
     * Moves the currently open menu to the given position.
     * @param x X coords for the top left position.
     * @param y Y coords for the top left position.
     */
    public void moveMenuTo(int x, int y) {
        menuX = x;
        menuY = y;

        update();
    }


    /**
     * Returns the right click menu.
     * @return Right click menu.
     */
    public WhiteBoardMenu getMenu() {
        return menu;
    }


    /**
     * Returns the edition shape menu.
     * @return Edition shape menu.
     */
    public EditionMenu getEditionMenu() {
        return editionMenu;
    }
}
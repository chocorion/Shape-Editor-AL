package application.view.areas;

import application.model.areas.WhiteBoard;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.Color;
import application.utils.ModelObserver;
import application.view.*;

import application.view.menu.EditionMenu;
import application.view.menu.EditionSubMenu;
import application.view.menu.WhiteBoardMenu;

import java.util.ArrayList;

public class WhiteBoardView implements ModelObserver {
    private WhiteBoard whiteBoard;
    private WhiteBoardMenu menu;
    private EditionMenu editionMenu;

    private ArrayList<Shape> selectedShapes;
    private Rectangle area;

    private ViewBridge view;

    private boolean isMenuOpen;
    private boolean isEditionMenuOpen;
    private int menuX, menuY;

    public WhiteBoardView(MainView mainView, ViewBridge view, WhiteBoard whiteBoard) {
        this.view = view;

        area = Layout.getWhiteBoard();

        this.whiteBoard = whiteBoard;
        menu = new WhiteBoardMenu(view);

        selectedShapes = new ArrayList<>();
        isMenuOpen = false;
        isEditionMenuOpen = false;
        editionMenu = new EditionMenu(view);
    }


    public void draw() {
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

        // Draw big white rectangle
        for (Shape shape:whiteBoard.getInnerShapes()) {
            shape.draw(view);
        }

        drawShapeSelection(selectedShapes);

        if (isMenuOpen)
            menu.draw(menuX, menuY);
        else if (isEditionMenuOpen)
            editionMenu.draw(menuX, menuY);
    }

    public void addSelection(ArrayList<Shape> shapes) {
        selectedShapes.addAll(shapes);
    }

    public ArrayList<Shape> getSelectedShapes() {
        return selectedShapes;
    }

    public void clearSelection() {
        selectedShapes.clear();
    }

    public void drawShapeSelection(ArrayList<Shape> shapes) {
        for (Shape shape : shapes) {
            drawShapeSelection(shape);
        }
    }

    public void drawShapeSelection(Shape shape) {
        int selectionMarge = 4;

        new Rectangle(
                shape.getMinX() - selectionMarge,
                shape.getMinY() - selectionMarge,
                shape.getMaxX() - shape.getMinX() + 2 * selectionMarge,
                shape.getMaxY() - shape.getMinY() + 2 * selectionMarge,
                Color.SELECTION
        ).draw(view);
    }

    @Override
    public void update() {

        area = Layout.getWhiteBoard();
        this.draw();
    }


    public void openWhiteboardMenu(int x, int y) {
        menuX = x;
        menuY = y;

        isMenuOpen = true;
        draw();
    }

    public void closeWhiteboardMenu() {
        isMenuOpen = false;
        draw();
    }

    public void openEditionMenu(int x, int y) {
        System.out.println("OPENING EDITION MENU");
        menuX = x;
        menuY = y;

        isMenuOpen = false;
        isEditionMenuOpen = true;
        draw();
    }

    public void closeEditionMenu() {
        isEditionMenuOpen = false;
        isMenuOpen = true;

        draw();
    }

    public WhiteBoardMenu getMenu() {
        return menu;
    }

    public EditionMenu getEditionMenu() {
        return editionMenu;
    }
}
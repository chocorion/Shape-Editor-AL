package application.view.areas;

import application.model.areas.WhiteBoard;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.ModelObservable;
import application.utils.ModelObserver;
import application.utils.Color;
import application.view.ObserverDecoration;
import application.view.View;
import application.view.ViewDecorator;
import application.view.decoration.WhiteBoardMenu;

public class WhiteBoardView extends ViewDecorator implements ObserverDecoration {
    private WhiteBoard whiteBoard;
    private WhiteBoardMenu menu;

    public WhiteBoardView(View view, WhiteBoard whiteBoard) {
        super(view);

        this.whiteBoard = whiteBoard;
        menu = new WhiteBoardMenu(this);
    }

    @Override
    public void draw() {
        super.draw();

        int borderSize = 1;

        super.drawRectangle(new Rectangle(whiteBoard.getX(), whiteBoard.getY(), whiteBoard.getWidth(), whiteBoard.getHeight(), Color.BLACK));
        super.drawRectangle(new Rectangle(whiteBoard.getX() + borderSize, whiteBoard.getY() + borderSize, whiteBoard.getWidth() - 2 * borderSize, whiteBoard.getHeight() - 2 * borderSize, Color.WHITE));

        // Draw big white rectangle
        for (Shape shape:whiteBoard.getInnerShapes()) {
            shape.draw(this);
        }
    }

    @Override
    public void update() {
        this.draw();
    }

    @Override
    public ModelObservable getSubject() {
        return this.whiteBoard;
    }

    /**
     * @brief open menu on whiteboard. If menu was already open, close the current one and re-
     *        open it at the new position.
     * @param x coord x of the menu in the model representation.
     * @param y coord y of the menu in the model representation.
     */
    public void openWhiteboardMenu(int x, int y) {
        if (menu.isToggle()) {
            menu.close();
        }

        menu.open(x, y);
    }

    public void closeWhiteboardMenu() {
        if (menu.isToggle()) {
            menu.close();
        }
    }

    public boolean isMenuOpen() {
        return menu.isToggle();
    }

    public boolean isInMenu(int x, int y) {
        return menu.getItemId(x, y) != -1;
    }
}
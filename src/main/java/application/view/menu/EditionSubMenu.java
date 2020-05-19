package application.view.menu;

import application.view.IDrawable;

/**
 * Interface for edition's submenu.
 */
public interface EditionSubMenu extends IDrawable {
    /**
     * Draws the sub menu at the given position.
     * @param x Top left x coords.
     * @param y Top left y coords.
     */
    void draw(int x, int y);


    /**
     * Get the name of the submenu.
     * @return Name of the submenu.
     */
    String getName();
}

package application.view.menu;

import application.view.IDrawable;

public interface EditionSubMenu extends IDrawable {
    void draw(int x, int y);
    String getName();
}

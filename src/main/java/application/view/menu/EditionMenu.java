package application.view.menu;

import application.model.shape.CompositeShape;
import application.model.shape.Polygon;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.Color;
import application.view.ViewBridge;
import application.view.element.TextButton;

import java.util.ArrayList;

/**
 * Represents the view for the shape edition menu.
 */
public class EditionMenu {
    private static EditionMenu instance;

    private static int width = 240;
    private static int height = 240;

    private static int margin = 4;

    private static int header_height = 20;
    private static int footer_height = 45;

    private static int subMenuX = margin;
    private static int subMenuY = 2 * margin + header_height;
    private static int subMenuWidth = width - 2 * margin;
    private static int subMenuHeight = height - 4 * margin - header_height - footer_height;


    private ArrayList<EditionSubMenu> subMenus;
    private ArrayList<TextButton> buttons;

    private int selectedMenu;

    private ViewBridge view;

    private int x, y;


    /**
     * Parameterized constructor.
     * @param view Bridge to use for drawign.
     */
    private EditionMenu(ViewBridge view) {
        this.view = view;

        subMenus = new ArrayList<>();
        buttons = new ArrayList<>();
    }


    /**
     * Build the header of the menus, with sub menu buttons.
     */
    private void buildHeader() {
        int numberButtons = subMenus.size();

        for (int i = 0; i < numberButtons; i++) {
            buttons.add(
                    new TextButton(
                            view,
                            i * width/numberButtons,
                            5,
                            width/numberButtons,
                            header_height,
                            subMenus.get(i).getName()
                    )
            );
        }
    }


    /**
     * Build the footer of the menu.
     */
    private void buildFooter() {
        int button_width = width/5;
        int button_height = (int) (footer_height * 0.7);

        int marge_x = (width - 3 * button_width)/4;
        int marge_y = (footer_height - button_height - margin)/2;

        buttons.add(
                new TextButton(view, marge_x, height - footer_height + marge_y, button_width, button_height, "Apply")
        );

        buttons.add(
                new TextButton(view, button_width + 2 * marge_x, height - footer_height + marge_y, button_width, button_height, "Reset")
        );

        buttons.add(
                new TextButton(view, 2 * button_width + 3 * marge_x, height - footer_height + marge_y, button_width, button_height, "Cancel")
        );
    }


    /**
     * Build all sub menus available for a rectangle.
     */
    private void buildRectangleSubMenu() {
        subMenus.clear();

        subMenus.add(0, new SubMenuColor(view, subMenuX, subMenuY, subMenuWidth, subMenuHeight));
        subMenus.add(1, new SubMenuResizeRectangle(view, subMenuX, subMenuY, subMenuWidth, subMenuHeight));
        subMenus.add(2, new SubMenuRotate(view, subMenuX, subMenuY, subMenuWidth, subMenuHeight));
        subMenus.add(3, new SubMenuRound(view, subMenuX, subMenuY, subMenuWidth, subMenuHeight));

        selectedMenu = 0;
    }


    /**
     * Build all sub menus available for a composite shape.
     */
    private void buildCompositeSubMenu() {
        subMenus.clear();

        subMenus.add(0, new SubMenuColor(view, subMenuX, subMenuY, subMenuWidth, subMenuHeight));
        subMenus.add(1, new SubMenuResizeGlobal(view, subMenuX, subMenuY, subMenuWidth, subMenuHeight));

        selectedMenu = 0;
    }


    /**
     * Build all sub menus available for a polygon.
     */
    private void buildPolygonSubMenu() {
        subMenus.clear();

        subMenus.add(0, new SubMenuColor(view, subMenuX, subMenuY, subMenuWidth, subMenuHeight));
        subMenus.add(1, new SubMenuResizeGlobal(view, subMenuX, subMenuY, subMenuWidth, subMenuHeight));
        subMenus.add(2, new SubMenuRotate(view, subMenuX, subMenuY, subMenuWidth, subMenuHeight));
        subMenus.add(3, new SubMenuPolygon(view, subMenuX, subMenuY, subMenuWidth, subMenuHeight));

        selectedMenu = 0;
    }


    /**
     * Returns the right menu for a given shape.
     * @param view Bridge to use for drawing.
     * @param shape Shape to build menu for.
     * @return Built edition menu.
     */
    public static EditionMenu getInstanceFor(ViewBridge view, Shape shape) {
        if (instance == null) {
            instance = new EditionMenu(view);
        }

        instance.buttons.clear();

        if (shape instanceof Rectangle)
            instance.buildRectangleSubMenu();

        else if (shape instanceof CompositeShape)
            instance.buildCompositeSubMenu();

        else if (shape instanceof Polygon)
            instance.buildPolygonSubMenu();


        instance.buildHeader();
        instance.buildFooter();

        return instance;
    }


    /**
     * Draw the edition menu to the given position.
     * @param x Top left x coords.
     * @param y Top left y coords.
     */
    public void draw(int x, int y) {
        this.x = x;
        this.y = y;

        view.drawRoundedRectShadow(x, y, width, height, 12, 3, Color.WHITE);

        for (TextButton button : buttons) {
            button.draw(this.x, this.y);
        }

        subMenus.get(selectedMenu).draw(x + margin, y + header_height + 2 * margin);

    }


    /**
     * Returns if a point is in this menu or not.
     * @param x X coords of the point.
     * @param y Y coords of the point.
     * @return True is the point is in, else false.
     */
    public boolean isIn(int x, int y) {
        return (x <= this.x + width && x >= this.x && y <= this.y + height && y >= this.y);
    }


    /**
     * Returns if a point is in the sub menu section.
     * @param x X coords of the point.
     * @param y Y coords of the point.
     * @return True is the point is in, else false.
     */
    public boolean isInSubmenu(int x, int y) {
        return isIn(x, y) && y >= this.y + header_height && y <= this.y + height - footer_height;
    }


    /**
     * Returns the currently selected submenu.
     * @return Currently selected submenu.
     */
    public EditionSubMenu getSelectedMenu() {
        return subMenus.get(selectedMenu);
    }


    /**
     * Return the button's index at the given position.
     * @param x X coords of the point.
     * @param y Y coords of the point.
     * @return Index of the button if exists, else -1.
     */
    public int getButtonId(int x, int y) {
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).isIn(x - this.x, y - this.y)) {
                return i;
            }
        }

        return -1;
    }


    /**
     * Return the number of submenu.
     * @return Number of submenu.
     */
    public int getSubmenuNumber() {
        return subMenus.size();
    }


    /**
     * Pushes the button at the given index.
     * @param buttonId index of the button to push.
     */
    public void pushButton(int buttonId) {
        if (buttonId >= 0 && buttonId < buttons.size()) {
            buttons.get(buttonId).push();
            draw(x, y);
        }
    }


    /**
     * Un-pushes the button at the given index.
     * @param buttonId index of the button to un-push.
     */
    public void unpushButton(int buttonId) {
        if (buttonId >= 0 && buttonId < buttons.size()) {
            buttons.get(buttonId).unpush();
            draw(x, y);
        }
    }


    /**
     * Switches the currently selected menu.
     * @param submenuId Index of the new selected menu.
     */
    public void switchSubmenu(int submenuId) {
        if (submenuId >= 0 && submenuId < subMenus.size()) {
            selectedMenu = submenuId;

            draw(this.x, this.y);
        }
    }


    /**
     * Return the top left x coords.
     * @return Top left x coords.
     */
    public int getX() {
        return this.x;
    }


    /**
     * Return the top left y coords.
     * @return Top left y coords.
     */
    public int getY() {
        return this.y;
    }
}

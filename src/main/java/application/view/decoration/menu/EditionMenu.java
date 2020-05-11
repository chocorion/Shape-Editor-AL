package application.view.decoration.menu;

import application.model.shape.Rectangle;
import application.utils.Color;
import application.view.ViewBridge;

import java.util.ArrayList;

public class EditionMenu {
    private static int width = 240;
    private static int height = 240;

    private static int margin = 4;

    private static int header_height = 20;
    private static int footer_height = 45;

    private ArrayList<SubMenu> subMenus;

    private int selectedMenu;

    private ViewBridge view;

    private int x, y;

    public EditionMenu(ViewBridge view) {
        this.view = view;

        int subMenuX = this.x + margin;
        int subMenuY = this.y + 2 * margin + header_height;
        int subMenuWidth = width - 2 * margin;
        int subMenuHeight = height - 4 * margin;

        subMenus = new ArrayList<>();

        subMenus.add(new subMenuColor(view, subMenuX, subMenuY, subMenuWidth, subMenuHeight));
        subMenus.add(new subMenuColor(view, subMenuX, subMenuY, subMenuWidth, subMenuHeight));
        subMenus.add(new subMenuColor(view, subMenuX, subMenuY, subMenuWidth, subMenuHeight));

        selectedMenu = 0;
    }


    public void draw(int x, int y) {
        this.x = x;
        this.y = y;

        view.drawRectangle(
                new Rectangle(this.x, this.y, width, height, new Color(178, 255, 171))
        );

        view.drawRectangle(
                new Rectangle(this.x + margin, this.y + margin, width - 2 * margin, header_height,
                        new Color(182, 232, 144))
        );

        view.drawRectangle(
                new Rectangle(this.x + margin, this.y + height - footer_height - margin, width - 2 * margin, footer_height,
                        new Color(234, 255, 158))
        );

        int buttonSize = (width - 2 * margin)/subMenus.size();
        int index = 0;
        for (SubMenu submenu : subMenus) {
            view.drawRectangle(new Rectangle(index * buttonSize + x + margin, y + margin, buttonSize, header_height));
            view.drawText(submenu.toString(),index * buttonSize + x + margin, y + margin + 10, buttonSize, Color.LIGHT_GREY);
            index++;
        }

        subMenus.get(selectedMenu).draw(x + margin, y + header_height + 2 * margin);
    }

    public boolean isIn(int x, int y) {
        return (x <= this.x + width && x >= this.x && y <= this.y + height && y >= this.y);
    }
}

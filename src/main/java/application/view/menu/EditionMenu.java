package application.view.menu;

import application.model.shape.Rectangle;
import application.utils.Color;
import application.view.ViewBridge;
import application.view.element.TextButton;

import java.util.ArrayList;

public class EditionMenu {
    private static int width = 240;
    private static int height = 240;

    private static int margin = 4;

    private static int header_height = 20;
    private static int footer_height = 45;

    private ArrayList<EditionSubMenu> subMenus;
    TextButton[] buttons;

    private int selectedMenu;

    private ViewBridge view;

    private int x, y;

    public EditionMenu(ViewBridge view) {
        this.view = view;

        int subMenuX = this.x + margin;
        int subMenuY = this.y + 2 * margin + header_height;
        int subMenuWidth = width - 2 * margin;
        int subMenuHeight = height - 4 * margin - header_height - footer_height;

        subMenus = new ArrayList<>();

        subMenus.add(new SubMenuColor(view, subMenuX, subMenuY, subMenuWidth, subMenuHeight));
        subMenus.add(new SubMenuColor(view, subMenuX, subMenuY, subMenuWidth, subMenuHeight));
        subMenus.add(new SubMenuColor(view, subMenuX, subMenuY, subMenuWidth, subMenuHeight));

        int button_width = width/5;
        int button_height = (int) (footer_height * 0.7);

        int marge_x = (width - 3 * button_width)/4;
        int marge_y = (footer_height - button_height - margin)/2;
        buttons = new TextButton[] {
                new TextButton(view, marge_x, height - footer_height + marge_y, button_width, button_height, "Apply"),
                new TextButton(view, button_width + 2 * marge_x, height - footer_height + marge_y, button_width, button_height, "Reset"),
                new TextButton(view, 2 * button_width + 3 * marge_x, height - footer_height + marge_y, button_width, button_height, "Cancel"),
        };

        selectedMenu = 0;
    }


    public void draw(int x, int y) {
        this.x = x;
        this.y = y;

        view.drawRoundedRectShadow(x, y, width, height, 12, 3, Color.WHITE);


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

        for (EditionSubMenu submenu : subMenus) {
            view.drawRectangle(new Rectangle(index * buttonSize + x + margin, y + margin, buttonSize, header_height));
            view.drawText(submenu.getName(),index * buttonSize + x + margin, y + margin + 10, buttonSize, Color.LIGHT_GREY);
            index++;
        }

        subMenus.get(selectedMenu).draw(x + margin, y + header_height + 2 * margin);

        for (TextButton button : buttons)
            button.draw(x, y);
    }

    public boolean isIn(int x, int y) {
        return (x <= this.x + width && x >= this.x && y <= this.y + height && y >= this.y);
    }

    public EditionSubMenu getSelectedMenu() {
        return subMenus.get(selectedMenu);
    }

    public int getButtonId(int x, int y) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isIn(x - this.x, y - this.y)) {
                return i;
            }
        }

        return -1;
    }

    public void pushButton(int buttonId) {
        if (buttonId >= 0 && buttonId < buttons.length) {
            buttons[buttonId].push();
            draw(x, y);
        }
    }

    public void unpushButton(int buttonId) {
        if (buttonId >= 0 && buttonId < buttons.length) {
            buttons[buttonId].unpush();
            draw(x, y);
        }
    }
}

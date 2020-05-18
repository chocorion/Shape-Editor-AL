package application.view.areas;

import application.model.shape.Rectangle;
import application.view.MainView;

public class Layout {
    public static final int RECOMMENDED_WIDTH = 640;
    public static final int RECOMMENDED_HEIGHT = 480;

    private static int width;
    private static int height;

    // Fixed value
    private static final int TOPBAR_HEIGHT = 30;
    private static final int TOOLBAR_WIDTH = 50;

    public static final int GAPS   = 2;
    public static final int BORDER = 1;


    public static void update(int width, int height) {
        Layout.width = width;
        Layout.height = height;
    }

    public static Rectangle getTopBar() {
        return new Rectangle(
                GAPS,
                GAPS,
                width - 2 * GAPS,
                TOPBAR_HEIGHT - 2 * GAPS
        );
    }

    public static Rectangle getToolBar() {
        return new Rectangle(
                GAPS,
                TOPBAR_HEIGHT + GAPS,
                TOOLBAR_WIDTH - 2 * GAPS,
                height - (TOPBAR_HEIGHT + 2 * GAPS)
        );
    }

    public static Rectangle getWhiteBoard() {
        return new Rectangle(
                TOOLBAR_WIDTH + GAPS,
                TOPBAR_HEIGHT + GAPS,
                width - TOOLBAR_WIDTH - 2 * GAPS,
                height - TOPBAR_HEIGHT - 2 * GAPS
        );
    }
}

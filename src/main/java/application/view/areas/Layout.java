package application.view.areas;

import application.model.shape.Rectangle;
import application.utils.Color;

/**
 * Static class that contains the position of elements int the model.
 */
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


    /**
     * Update elements position according to the new windows size.
     * @param width New width of the window<.
     * @param height New height of the windows.
     */
    public static void update(int width, int height) {
        Layout.width = width;
        Layout.height = height;
    }


    /**
     * Return the top bar position.
     * @return Top bar position.
     */
    public static Rectangle getTopBar() {
        return new Rectangle(
                GAPS,
                GAPS,
                width - 2 * GAPS,
                TOPBAR_HEIGHT - 2 * GAPS
               //new Color(77, 104, 119)
        );
    }


    /**
     * Return the tool bar position.
     * @return Tool bar position.
     */
    public static Rectangle getToolBar() {
        return new Rectangle(
                GAPS,
                TOPBAR_HEIGHT + GAPS,
                TOOLBAR_WIDTH - 2 * GAPS,
                height - (TOPBAR_HEIGHT + 2 * GAPS)
                //new Color(77, 104, 119)
        );
    }


    /**
     * Return the whiteboard position.
     * @return Whiteboard position.
     */
    public static Rectangle getWhiteBoard() {
        return new Rectangle(
                TOOLBAR_WIDTH + GAPS,
                TOPBAR_HEIGHT + GAPS,
                width - TOOLBAR_WIDTH - 2 * GAPS,
                height - TOPBAR_HEIGHT - 2 * GAPS
        );
    }
}

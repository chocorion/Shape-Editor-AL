package application.view;

import application.model.Model;
import application.model.shape.Rectangle;
import application.utils.Color;
import application.view.areas.Layout;
import application.view.areas.ToolBarView;
import application.view.areas.TopBarView;
import application.view.areas.WhiteBoardView;

/**
 * Represent the main view of the application.
 */
public class MainView {
    private ToolBarView     toolBar;
    private TopBarView      topBar;
    private WhiteBoardView  whiteBoard;

    private int windowsWidth;
    private int windowsHeight;

    private Model model;
    private ViewBridge view;


    /**
     * Parameterized constructor.
     * @param model Model to use, containing data.
     * @param viewBridge Bridge to use for the draw.
     */
    public MainView(Model model, ViewBridge viewBridge) {
        this.model = model;
        view = viewBridge;

        windowsWidth  = 480;
        windowsHeight = 480;

        Layout.update(windowsWidth, windowsHeight);

        toolBar    = new ToolBarView(view, model.getToolBar());
        topBar     = new TopBarView(view);
        whiteBoard = new WhiteBoardView(view, model.getWhiteBoard());

    }


    /**
     * Update the view, and all its elements.
     */
    public void update() {
        Layout.update(windowsWidth, windowsHeight);

        view.drawRectangle(new Rectangle(0, 0, windowsWidth, windowsHeight, new Color(255, 255, 255, 1)));

        toolBar.update();
        topBar.update();
        whiteBoard.update();
    }


    /**
     * Method to call when the size of the windows change.
     * @param newWidth New width of the windows, in pixels.
     * @param newHeight New height of the windows, in pixels.
     */
    public void changeSize(int newWidth, int newHeight) {
        windowsWidth = newWidth;
        windowsHeight = newHeight;

        update();
    }


    /**
     * Returns the top bar view.
     * @return Top bar view.
     */
    public TopBarView getTopBar() {
        return this.topBar;
    }


    /**
     * Returns the tool bar view.
     * @return Tool bar view.
     */
    public ToolBarView getToolBar() {
        return toolBar;
    }


    /**
     * Return the whiteboard view.
     * @return Whiteboard view.
     */
    public WhiteBoardView getWhiteBoard() {
        return whiteBoard;
    }


    /**
     * Return the current width of the windows.
     * @return Width of the windows.
     */
    public int getWidth() {
        return windowsWidth;
    }


    /**
     * Return the current height of the windows.
     * @return Current height of the windows.
     */
    public int getHeight() {
        return windowsHeight;
    }


    /**
     * Return the current bridge to the drawer.
     * @return Bridge to the drawer.
     */
    public ViewBridge getViewBridge() {
        return view;
    }


    /**
     * Save the whiteboard.
     */
    public void save() {
        whiteBoard.drawSaveMenu();
    }


    /**
     * Load the whiteboard.
     */
    public void load() {
        whiteBoard.drawLoadMenu();
    }
}

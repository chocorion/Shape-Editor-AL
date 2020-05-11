package application.view;

import application.model.Model;
import application.model.shape.Rectangle;
import application.utils.Color;
import application.view.areas.Layout;
import application.view.areas.ToolBarView;
import application.view.areas.TopBarView;
import application.view.areas.WhiteBoardView;

public class MainView {
    private ToolBarView     toolBar;
    private TopBarView      topBar;
    private WhiteBoardView  whiteBoard;

    private int windowsWidth;
    private int windowsHeight;

    private Model model;
    private ViewBridge view;

    public MainView(Model model, ViewBridge viewBridge) {
        this.model = model;
        view = viewBridge;

        windowsWidth  = 480;
        windowsHeight = 480;

        Layout.update(windowsWidth, windowsHeight);

        toolBar    = new ToolBarView(this, view, model.getToolBar());
        topBar     = new TopBarView(this, view);
        whiteBoard = new WhiteBoardView(this, view, model.getWhiteBoard());

    }

    public void update() {
        Layout.update(windowsWidth, windowsHeight);

        view.drawRectangle(new Rectangle(0, 0, windowsWidth, windowsHeight, new Color(255, 255, 255, 1)));

        toolBar.update();
        topBar.update();
        whiteBoard.update();
    }

    public void changeSize(int newWidth, int newHeight) {
        windowsWidth = newWidth;
        windowsHeight = newHeight;

        update();
    }

    public TopBarView getTopBar() {
        return this.topBar;
    }

    public ToolBarView getToolBar() {
        return toolBar;
    }

    public WhiteBoardView getWhiteBoard() {
        return whiteBoard;
    }

    public int getWidth() {
        return windowsWidth;
    }

    public int getHeight() {
        return windowsHeight;
    }

    public ViewBridge getViewBridge() {
        return view;
    }
}

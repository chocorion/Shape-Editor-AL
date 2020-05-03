package application.view;

import application.model.Model;
import application.view.areas.Layout;
import application.view.areas.ToolBarView;
import application.view.areas.TopBarView;
import application.view.areas.WhiteBoardView;
import application.view.decoration.Menu;
import application.view.decoration.WhiteBoardMenu;

public class MainView {
    private ToolBarView     toolBar;
    private TopBarView      topBar;
    private WhiteBoardView  whiteBoard;

    private int windowsWidth;
    private int windowsHeight;

    private Model model;

    public MainView(Model model, View view) {
        this.model = model;

        windowsWidth  = 480;
        windowsHeight = 480;

        toolBar    = new ToolBarView(this, view, model.getToolBar());
        topBar     = new TopBarView(this, view);
        whiteBoard = new WhiteBoardView(this, view, model.getWhiteBoard());

    }

    public void update() {
        Layout.update(windowsWidth, windowsHeight);

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
}

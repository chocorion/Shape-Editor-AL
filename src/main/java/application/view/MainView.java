package application.view;

import application.model.Model;
import application.view.areas.ToolBarView;
import application.view.areas.TopBarView;
import application.view.areas.WhiteBoardView;

public class MainView {
    private ToolBarView toolBar;
    private TopBarView topBar;
    private WhiteBoardView whiteBoard;

    private Model model;

    public MainView(Model model, View viewImp) {
        this.model = model;

        this.toolBar    = new ToolBarView(viewImp, model.getToolBar());
        this.topBar     = new TopBarView(viewImp, model.getTopBar());
        this.whiteBoard = new WhiteBoardView(viewImp, model.getWhiteBoard());
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
}

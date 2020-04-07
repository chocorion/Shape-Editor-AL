package application;

import application.model.Model;
import application.ui.javafx.ViewFx;
import application.view.SimpleView;
import application.view.ToolBarView;
import application.view.View;
import application.view.WhiteBoardView;


public class Main {
    private static Model model;
    private static View view;

    public static void main(String[] args) {
        System.out.println("Hello world !");

        model = new Model();
        buildView();
    }


    public static void buildView() {
        ViewFx viewFx = new ViewFx(model);
        View simpleView = new SimpleView(viewFx);

        WhiteBoardView whiteBoard = new WhiteBoardView(simpleView, model.getWhiteBoard());
        ToolBarView toolBar = new ToolBarView(whiteBoard, model.getToolBar());

        model.getToolBar().attachObserver(toolBar);
        model.getWhiteBoard().attachObserver(whiteBoard);

        view = toolBar;
        viewFx.initViewFx();
    }


}

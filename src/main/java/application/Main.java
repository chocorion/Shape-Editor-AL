package application;

import application.model.Model;
import application.ui.javafx.ViewFx;
import application.view.SimpleView;
import application.view.ToolBar;
import application.view.View;
import application.view.WhiteBoard;


public class Main {
    private static Model model;
    private static View view;

    public static void main(String[] args) {
        System.out.println("Hello world !");

        model = new Model();
        buildView();

    }


    public static void buildView() {
        ViewFx viewFx = new ViewFx();
        viewFx.initViewFx();

        View view = new SimpleView(viewFx);

        WhiteBoard whiteBoard = new WhiteBoard(view);
        ToolBar toolBar = new ToolBar(whiteBoard);

        model.getToolBar().attachObserver(toolBar);
        model.getWhiteBoard().attachObserver(whiteBoard);

        view = toolBar;
    }
}

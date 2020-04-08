package application;

import application.controller.Controller;
import application.controller.ControllerFx;
import application.controller.MainController;
import application.model.Model;
import application.ui.javafx.ViewFx;
import application.view.*;


public class Main {
    private static Model model;
    private static View view;
    private static MainController controller;

    public static void main(String[] args) {
        System.out.println("Hello world !");

        model = new Model();
        buildUi();

    }


    public static void buildUi() {
        ViewFx viewFx = new ViewFx(model);
        View simpleView = new SimpleView(viewFx);

        WhiteBoardView whiteBoard = new WhiteBoardView(simpleView, model);
        ToolBarView toolBar = new ToolBarView(whiteBoard, model);
        TopBarView topBar = new TopBarView(whiteBoard, model.getTopBar());

        model.getToolBar().attachObserver(toolBar);
        model.getWhiteBoard().attachObserver(whiteBoard);
        model.getTopBar().attachObserver(topBar);

        view = toolBar;
        controller = new MainController(model, view, viewFx);
        viewFx.initViewFx();
    }


}

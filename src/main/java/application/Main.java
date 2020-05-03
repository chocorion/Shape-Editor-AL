package application;

import application.controller.MainController;
import application.model.Model;
import application.ui.javafx.ViewFx;
import application.view.*;


public class Main {
    private static Model model;
    private static MainView view;
    private static MainController controller;

    public static void main(String[] args) {
        System.out.println("Hello world !");

        model = new Model();
        buildUi();

    }


    public static void buildUi() {
        ViewFx viewFx = new ViewFx(model);
        View viewBridge = new ViewBridge(viewFx);

        view = new MainView(model, viewBridge);

        model.getToolBar().attachObserver(view.getToolBar());
        model.getWhiteBoard().attachObserver(view.getWhiteBoard());

        controller = new MainController(model, view, viewFx);
        viewFx.initViewFx();
    }


}

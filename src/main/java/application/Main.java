package application;

import application.controller.MainController;
import application.model.Model;
import application.ui.UIFactory;
import application.ui.javafx.UIFx;
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
        UIFactory uiFactory = new UIFx();

        ConcreteViewItf concreteView = uiFactory.getView(model);
        ViewBridge viewBridge = new ViewBridge(concreteView);

        view = new MainView(model, viewBridge);

        model.getToolBar().attachObserver(view.getToolBar());
        model.getWhiteBoard().attachObserver(view.getWhiteBoard());

        controller = new MainController(model, view);
        uiFactory.getController(controller, concreteView);

        ((ViewFx) concreteView).initViewFx();
    }


}

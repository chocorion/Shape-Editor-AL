package application;

import application.controller.MainController;
import application.model.Model;
import application.ui.UIFactory;
import application.ui.javafx.UIFx;
import application.ui.javafx.ViewFx;
import application.view.*;


/**
 * Entry point of the application
 */
public class Main {
    private static Model model;
    private static MainView view;
    private static MainController controller;

    public static void main(String[] args) {
        System.out.println("Hello world !");

        model = new Model();
        buildUi();

    }


    /**
     * Build and assemble all the elements of the ui.
     */
    public static void buildUi() {
        UIFactory uiFactory = new UIFx();

        IConcreteView concreteView = uiFactory.getView(model);


        view = new MainView(model, concreteView);

        model.getToolBar().attachObserver(view.getToolBar());
        model.getWhiteBoard().attachObserver(view.getWhiteBoard());

        controller = new MainController(model, view);
        uiFactory.getController(controller, concreteView);

        ((ViewFx) concreteView).initViewFx();
    }


}

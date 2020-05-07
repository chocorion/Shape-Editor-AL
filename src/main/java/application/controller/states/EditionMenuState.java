package application.controller.states;

import application.controller.MainController;
import application.model.Model;
import application.view.MainView;
import application.view.decoration.menu.WhiteBoardMenu;

import javax.swing.text.DefaultTextUI;

public class EditionMenuState extends ControllerStateImp {
    private static EditionMenuState state;

    MainController mainController;
    Model model;
    MainView view;

    private EditionMenuState(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;
    }

    public static void setInstance(MainController mainController, Model model, MainView view) {
        state = new EditionMenuState(mainController, model, view);
    }


    public static EditionMenuState getInstance() {
        return state;
    }

    @Override
    public boolean onLeftClickReleased(int x, int y) {
        if (!view.getWhiteBoard().getMenu().isIn(x, y)) {
            view.getWhiteBoard().getMenu().closeEditionMenu();
            mainController.switchState(WhiteBoardMenuState.getInstance());
            return true;
        }
        return true;
    }
}

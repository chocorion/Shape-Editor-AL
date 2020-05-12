package application.controller.states;

import application.controller.MainController;
import application.model.Model;
import application.view.MainView;
import application.view.menu.EditionMenu;
import application.view.menu.SubMenuColor;


public class EditionMenuStateColor extends ControllerStateImp {
    private static EditionMenuStateColor state;

    MainController mainController;
    Model model;
    MainView view;

    EditionMenu menu;
    SubMenuColor menuColor;
    int sliderId;
    int buttonId;

    private EditionMenuStateColor(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;

        menu = view.getWhiteBoard().getEditionMenu();
        menuColor = (SubMenuColor) menu.getSelectedMenu();

        sliderId = -1;
        buttonId = -1;
    }

    public static void setInstance(MainController mainController, Model model, MainView view) {
        state = new EditionMenuStateColor(mainController, model, view);
    }


    public static EditionMenuStateColor getInstance() {
        return state;
    }

    @Override
    public boolean onLeftClickReleased(int x, int y) {
        if (sliderId != -1) {
            sliderId = -1;
            return true;
        }

        if (buttonId != -1) {
            view.getWhiteBoard().getEditionMenu().unpushButton(buttonId);
            buttonId = -1;
            return true;
        }

        if (!menu.isIn(x, y)) {
            view.getWhiteBoard().closeEditionMenu();
            mainController.switchState(WhiteBoardMenuState.getInstance());
            return true;
        }


        return true;
    }

    @Override
    public boolean onLeftClickPressed(int x, int y) {
        // TODO Clean toute cette partie, la, c'est trop moche
        if (menu.isIn(x, y)) {
            sliderId = menuColor.getSliderId(x, y);
            buttonId = view.getWhiteBoard().getEditionMenu().getButtonId(x, y);

            if (buttonId != -1) {
                view.getWhiteBoard().getEditionMenu().pushButton(buttonId);
            }
        }

        return true;
    }

    @Override
    public boolean onMouseMoved(int x, int y) {
        if (sliderId != -1)
            menuColor.moveSlider(x, y, sliderId);
        return true;
    }

    @Override
    public String toString() {
        return "edition menu";
    }
}

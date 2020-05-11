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

    private EditionMenuStateColor(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;
        this.model = model;
        this.view = view;

        menu = view.getWhiteBoard().getEditionMenu();
        menuColor = (SubMenuColor) menu.getSelectedMenu();

        sliderId = -1;
    }

    public static void setInstance(MainController mainController, Model model, MainView view) {
        state = new EditionMenuStateColor(mainController, model, view);
    }


    public static EditionMenuStateColor getInstance() {
        return state;
    }

    @Override
    public boolean onLeftClickReleased(int x, int y) {
        if (!menu.isIn(x, y)) {
            view.getWhiteBoard().closeEditionMenu();
            mainController.switchState(WhiteBoardMenuState.getInstance());
            return true;
        }


        return true;
    }

    @Override
    public boolean onLeftClickPressed(int x, int y) {
        // Vérifier si le clique n'a pas eu lieux sur la barre ongle
        if (menu.isIn(x, y)) {
            System.out.println("Is in");
            sliderId = menuColor.getSliderId(x, y);
            System.out.println("Slider id -> " + sliderId);
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

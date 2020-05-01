package application.controller.states;

import application.controller.MainController;
import application.model.Model;
import application.view.MainView;

public class Menu extends ControllerStateImp {
    private static Menu instance;
    private final MainController mainController;

    private final Model model;
    private final MainView view;

    private Menu(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;

        this.model = model;
        this.view = view;
    }

    @Override
    public boolean onLeftClickPressed(int x, int y) {
        if (view.getWhiteBoard().isInMenu(x, y)) {
            System.out.println("Click on menu");

        } else {
            view.getWhiteBoard().clearSelection();
            view.getWhiteBoard().closeWhiteboardMenu();

            mainController.switchState(Default.getInstance());
        }

        return true;
    }

    @Override
    public boolean onLeftClickReleased(int x, int y) {
        /*
        Chercher la bonne case du menu, réaliser la bonne action
        mainController.addCommand(
                        new AddComposite(model, selectionStartX, selectionStartY, selectionEndX, selectionEndY)
                );
         */
        return true;
    }

    @Override
    public boolean onRightClickPressed(int x, int y) {
        view.getWhiteBoard().openWhiteboardMenu(x, y);
        return true;
    }


    public static Menu getInstance() {
        return instance;
    }

    public static void setInstance(MainController mainController, Model model, MainView view) {
        instance = new Menu(mainController, model, view);
    }

    @Override
    public String toString() {
        return "menu";
    }
}

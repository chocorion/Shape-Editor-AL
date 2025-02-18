package application.controller.states;

import application.controller.MainController;
import application.model.Model;
import application.model.command.concreteCommand.AddComposite;
import application.model.command.concreteCommand.ExpandComposite;
import application.model.shape.CompositeShape;
import application.model.shape.Shape;
import application.view.MainView;
import application.view.menu.WhiteBoardMenu;

import java.util.HashSet;


/**
 * Represent the state of the mainController when the
 * whiteBoard menu is open.
 */
public class WhiteBoardMenuState extends ControllerStateImp {
    private static WhiteBoardMenuState instance;
    private final MainController mainController;

    private final Model model;
    private final MainView view;

    /**
     * Parameterized constructor.
     * @param mainController The current mainController.
     * @param model The current model of the application.
     * @param view the current mainView of the application.
     */
    private WhiteBoardMenuState(MainController mainController, Model model, MainView view) {
        this.mainController = mainController;

        this.model = model;
        this.view = view;
    }


    /**
     * Set the instance.
     * @param mainController The current mainController.
     * @param model The current model of the application.
     * @param view the current mainView of the application.
     */
    public static void setInstance(MainController mainController, Model model, MainView view) {
        instance = new WhiteBoardMenuState(mainController, model, view);
    }


    /**
     * Return the current instance.
     * @return Current instance.
     */
    public static WhiteBoardMenuState getInstance() {
        return instance;
    }


    @Override
    public boolean onLeftClickPressed(int x, int y) {
        return true;
    }


    @Override
    public boolean onLeftClickReleased(int x, int y) {
        WhiteBoardMenu menu = view.getWhiteBoard().getMenu();
        int itemId = menu.getItemId(x, y);

        if (itemId == 0) {
            model.execute(
                    new AddComposite(model.getWhiteBoard(), view.getWhiteBoard().getSelectedShapes())
            );

            closeAndSwitch();

        } else if (itemId == 1) {
            HashSet<Shape> selection = view.getWhiteBoard().getSelectedShapes();

            if (selection.size() == 1) {
                Shape s = selection.iterator().next();
                if (s instanceof CompositeShape) {
                    model.execute(
                            new ExpandComposite(model.getWhiteBoard(), (CompositeShape) s)
                    );
                }

                closeAndSwitch();
            }
        } else if (itemId == 2) {
            HashSet<Shape> selectedShapes = view.getWhiteBoard().getSelectedShapes();
            Shape selected;

            if (selectedShapes.size() == 1) {
                selected = selectedShapes.iterator().next();

                view.getWhiteBoard().openEditionMenu(x, y, selected);
                mainController.switchState(EditionMenuState.getInstance());
            } else {
                System.out.println("Can't open edition menu, more than one shape in selection !");
            }

            return true;
        } else {
            closeAndSwitch();
        }

        return true;
    }


    @Override
    public boolean onRightClickPressed(int x, int y) {
        view.getWhiteBoard().openWhiteboardMenu(x, y);
        return true;
    }


    @Override
    public boolean onMouseMoved(int x, int y) {
        WhiteBoardMenu menu = view.getWhiteBoard().getMenu();
        int itemId = menu.getItemId(x, y);

        menu.setCurrentlySelected(itemId);

        return true;
    }


    private void closeAndSwitch() {
        view.getWhiteBoard().clearSelection();
        view.getWhiteBoard().closeWhiteboardMenu();

        mainController.switchState(DefaultState.getInstance());
    }


    @Override
    public String toString() {
        return "menu";
    }
}

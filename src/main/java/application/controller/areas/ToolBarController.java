package application.controller.areas;

import application.controller.MainController;
import application.model.areas.ToolBar;
import application.model.command.concreteCommand.AddShape;
import application.model.shape.Shape;
import application.view.areas.ToolBarView;

public class ToolBarController {
    private ToolBar model;
    private ToolBarView view;
    private MainController mainController;

    public ToolBarController(MainController mainController, ToolBar model, ToolBarView view) {
        this.mainController = mainController;

        this.model = model;
        this.view = view;
    }

    public void onLeftClickPressed(int x, int y) {
        System.out.println("Click on Toolbar !");
        mainController.setHoldedShape(model.getShape(x, y), model);
    }

    public void onLeftClickReleased(int x, int y) {
        if (mainController.isHoldingShape() && mainController.getHoldedShapeOrigin() != model) {
            mainController.addCommand(new AddShape(model, mainController.getHoldedShapeClone()));
        } else {
            System.out.println("Set holded shape -> " + model.getShape(x, y));
            mainController.setHoldedShape(model.getShape(x, y), model);
        }
    }
}

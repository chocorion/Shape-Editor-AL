package application.controller;

import application.model.Model;
import application.model.command.concreteCommand.AddShape;
import application.model.shape.Shape;
import application.ui.javafx.ViewFx;
import application.view.ConcreteViewItf;
import application.view.View;

public class MainController {
    private Model model;
    private View view;
    private Controller controllerImp;

    private Shape holdedShape;

    public MainController(Model model, View view, ConcreteViewItf viewImp) {
        this.model = model;
        this.view = view;
        this.holdedShape = null;

        this.controllerImp = new ControllerFx(this, (ViewFx) viewImp);
        ((ViewFx) viewImp).AddController(this.controllerImp);
    }

    public void onLeftClickPressed(double x, double y) {
        System.out.println("Left Click pressed on " + x + " " + y);

        if (this.model.getToolBar().isIn((int) x, (int) y)) {
            System.out.println("Click on Toolbar !");
            this.holdedShape = this.model.getToolBar().getShape((int) x, (int) y);
        }
    }

    public void onRightClickPressed(double x, double y) {
        System.out.println("Right Click pressed on " + x + " " + y);

    }

    public void onLeftClickReleased(double x, double y) {
        System.out.println("Left Click released on " + x + " " + y);
        if (this.holdedShape != null && this.model.getWhiteBoard().isIn((int) x, (int) y)) {
            Shape clone = (Shape) holdedShape.clone();
            clone.moveTo((int) x, (int) y);
            this.model.execute(new AddShape(this.model.getWhiteBoard(), clone));



            System.out.println("Adding shape in WHITEBOARD");
        } else if (this.holdedShape == null) {
            if (this.model.getTopBar().isIn((int) x, (int) y)) {
                this.model.getTopBar().clickOnButton((int) x, (int) y);
            }
        }
        this.holdedShape = null;
    }

    public void onRightClickReleased(double x, double y) {
        System.out.println("Right Click released on " + x + " " + y);
    }

    public void onMouseDragged(double x, double y) {
        System.out.println("Mouse dragged on " + x + " " + y);
    }

}

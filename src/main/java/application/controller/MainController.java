package application.controller;

import application.controller.areas.ToolBarController;
import application.controller.areas.TopBarController;
import application.controller.areas.WhiteBoardController;
import application.model.Model;
import application.model.command.Command;
import application.model.shape.Shape;
import application.ui.javafx.ViewFx;
import application.utils.ShapeContainer;
import application.view.ConcreteViewItf;
import application.view.MainView;

public class MainController {
    private Model model;
    private MainView view;
    private Controller controllerImp;

    private TopBarController     topBarController;
    private ToolBarController    toolBarController;
    private WhiteBoardController whiteBoardController;

    private Shape holdedShape;
    private ShapeContainer holdedShapeOrigin;

    private boolean clickLeft, drag, select, menu;
    private int beginX,beginY,endX,endY;

    public MainController(Model model, MainView view, ConcreteViewItf viewImp) {
        this.model = model;
        this.view = view;

        this.holdedShape = null;
        this.holdedShapeOrigin=null;

        this.controllerImp = new ControllerFx(this, (ViewFx) viewImp);
        ((ViewFx) viewImp).AddController(this.controllerImp);

        topBarController     = new TopBarController(this, model.getTopBar(), view.getTopBar());
        toolBarController    = new ToolBarController(this, model.getToolBar(), view.getToolBar());
        whiteBoardController = new WhiteBoardController(this, model.getWhiteBoard(), view.getWhiteBoard());
    }

    public void onLeftClickPressed(double x, double y) {
        System.out.println("Left Click pressed on " + x + " " + y);

        if (model.getTopBar().isIn((int) x, (int) y)) {
            topBarController.onLeftClickPressed((int) x, (int) y);
        }

        else if (this.model.getToolBar().isIn((int) x, (int) y)) {
            toolBarController.onLeftClickPressed((int) x, (int) y);
        }

        else if(this.model.getWhiteBoard().isIn((int)x,(int)y)) {
            whiteBoardController.onLeftClickPressed((int) x, (int) y);
            this.clickLeft = true;
        }
    }

    public void onRightClickPressed(double x, double y) {
        if(!menu){
            view.getWhiteBoard().addPopUpMenu((int)x,(int)y);
            menu = true;
        }
    }

    public void onLeftClickReleased(double x, double y) {
        if(this.model.getWhiteBoard().isIn((int)x, (int)y)) {
            whiteBoardController.onLeftClickReleased((int) x, (int) y);
        }

        else if (model.getToolBar().isIn((int) x, (int) y)) {
            toolBarController.onLeftClickReleased((int) x, (int) y);
        }

        this.clickLeft = false;
        this.drag = false;
    }

    public void onRightClickReleased(double x, double y) {
        System.out.println("Right Click released on " + x + " " + y);
    }

    public void onMouseDragged(double x, double y) {
        this.drag = true;
    }

    public void setHoldedShape(Shape shape, ShapeContainer origin) {
        this.holdedShape = shape;
        this.holdedShapeOrigin = origin;
    }

    public boolean isMenuOpen() {
        return this.menu;
    }

    public boolean isSelectionSet() {
        return this.select;
    }

    public void setSelection(boolean b) {
        this.select = b;
    }

    public boolean isDraggeg() {
        return this.drag;
    }

    public void setDrag(boolean b) {
        this.drag = b;
    }

    public void setMenu(boolean b) {
        this.menu = b;
    }

    public void addCommand(Command cmd) {
        this.model.execute(cmd);
    }

    public boolean isHoldingShape() {
        return this.holdedShape != null;
    }

    public Shape getHoldedShapeClone() {
        return (Shape) this.holdedShape.clone();
    }

    public ShapeContainer getHoldedShapeOrigin() {
        return holdedShapeOrigin;
    }
}



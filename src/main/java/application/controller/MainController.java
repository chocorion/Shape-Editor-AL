package application.controller;

import application.model.Model;
import application.model.command.concreteCommand.AddComposite;
import application.model.command.concreteCommand.AddShape;
import application.model.shape.Shape;
import application.ui.javafx.ViewFx;
import application.utils.ShapeContainer;
import application.view.ConcreteViewItf;
import application.view.View;

public class MainController {
    private Model model;
    private View view;
    private Controller controllerImp;

    private Shape holdedShape;
    private ShapeContainer holdedShapeOrigin;

    private boolean clickLeft, drag, select, menu;
    private int beginX,beginY,endX,endY;

    public MainController(Model model, View view, ConcreteViewItf viewImp) {
        this.model = model;
        this.view = view;

        this.holdedShape = null;
        this.holdedShapeOrigin=null;

        this.controllerImp = new ControllerFx(this, (ViewFx) viewImp);
        ((ViewFx) viewImp).AddController(this.controllerImp);
    }

    public void onLeftClickPressed(double x, double y) {
        System.out.println("Left Click pressed on " + x + " " + y);

        if (this.model.getToolBar().isIn((int) x, (int) y)) {
            System.out.println("Click on Toolbar !");
            this.holdedShape = this.model.getToolBar().getShape((int) x, (int) y);
            this.holdedShapeOrigin = this.model.getToolBar();
        }
        else if(this.model.getWhiteBoard().isIn((int)x,(int)y)){
            System.out.println("WHITEBOARD");
            if (!select) {
                this.beginX = (int)x;
                this.beginY =(int)y;
            }
            this.clickLeft = true;

            Shape currentShape = model.getWhiteBoard().getShapeAt((int) x, (int) y);
            if (currentShape != null) {
                this.holdedShape = currentShape;
                this.holdedShapeOrigin = model.getWhiteBoard();
            }
        }

    }

    public void onRightClickPressed(double x, double y) {
        System.out.println("Right Click pressed on " + x + " " + y);
        if( !menu){
            view.addPopUpMenu((int)x,(int)y);
            menu = true;
        }

    }

    public void onLeftClickReleased(double x, double y) {

        if(this.model.getWhiteBoard().isIn((int)x, (int)y) && clickLeft && drag) {
            System.out.println("SELECTION");
            this.endX = (int) x;
            this.endY = (int) y;
            this.view.drawSelection(beginX, beginY, Math.abs(beginX - endX), Math.abs(beginY - endY));
            this.select = true;
        }
        else if(this.menu){
            if(view.clickOnGroup((int) x , (int) y) && select) {
                view.undrawSelect(beginX, beginY,Math.abs(beginX - endX), Math.abs(beginY - endY));
                view.undrawMenu();
                this.model.execute(new AddComposite(this.model.getWhiteBoard(), beginX, beginY,endX,endY));
                this.model.update();
            }
            else {
                System.out.println("raté");
            }
            menu = false;
            select = false;


        }else if (this.holdedShape != null && this.model.getWhiteBoard().isIn((int) x, (int) y)) {
            Shape clone = (Shape) holdedShape.clone();
            clone.moveTo((int) x, (int) y);
            this.model.execute(new AddShape(this.model.getWhiteBoard(), clone));

        }
        else if (this.holdedShape != null && this.holdedShapeOrigin != model.getToolBar() && model.getToolBar().isIn((int) x, (int) y)) {
        this.model.execute(new AddShape(this.model.getToolBar(), (Shape) holdedShape.clone()));

        }    else if (this.holdedShape == null) {
            holdedShape.moveTo((int) x, (int) y);
            this.model.execute(new AddShape(this.model.getWhiteBoard(), holdedShape));
            System.out.println("Adding shape in WHITEBOARD");

        }


        this.clickLeft = false;
        this.drag = false;
    }

    public void onRightClickReleased(double x, double y) {
        System.out.println("Right Click released on " + x + " " + y);
    }

    public void onMouseDragged(double x, double y) {
        // System.out.println("Mouse dragged on " + x + " " + y);
        System.out.println("Mouse dragged on " + x + " " + y );
        this.drag = true;
    }



}

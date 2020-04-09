package application.view;

import application.model.shape.Polygon;
import application.model.shape.Rectangle;

public class SimpleView implements View {
    private ConcreteViewItf implementation;

    public SimpleView(ConcreteViewItf concreteView) {
        this.implementation = concreteView;
    }

    @Override
    public void draw() {
        // Refresh display ?
    }

    @Override
    public int getWidth() {
        return this.implementation.getWidth();
    }

    @Override
    public int getHeight() {
        return this.implementation.getHeight();
    }

    @Override
    public void drawRectangle(Rectangle rectangle) {
        System.out.println("DrawRect in SimpleView !");
        this.implementation.devDrawRectangle(rectangle);
    }

    @Override
    public void drawPolygon(Polygon polygon) {
        this.implementation.devDrawPolygon(polygon);
    }

    @Override
    public void drawSelection(int x, int y, int width, int height){
        System.out.println("drawSelect in SimpleView !");
        this.implementation.devDrawSelection(x,y,width,height);
    }
    @Override
    public void addPopUpMenu(int x, int y){
        System.out.println("addMenu in simpleView");
        this.implementation.devAddPopUpMenu( x,  y);

    }

    @Override
    public boolean clickOnGroup(int x,int y){return this.implementation.devClickOnGroup(x,y);}

    @Override
    public void undrawSelect(int x, int y, int width, int height) {this.implementation.devUndrawSelect(x,y,width,height);}

    @Override
    public void undrawMenu(){this.implementation.devUndrawMenu();}

}

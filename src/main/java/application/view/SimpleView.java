package application.view;

import application.model.shape.Polygon;
import application.model.shape.Rectangle;
import application.utils.Color;

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
        this.implementation.devDrawRectangle(rectangle);
    }

    @Override
    public void drawStrokeRectangle(Rectangle rectangle) {
        this.implementation.devDrawStrokeRectangle(rectangle);
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
    public void drawText(String text, int x, int y, int size, Color color) {
        this.implementation.devDrawText(text, x, y, size, color);
    }


    @Override
    public void undrawSelect(int x, int y, int width, int height) {this.implementation.devUndrawSelect(x,y,width,height);}

    @Override
    public void undrawMenu(){this.implementation.devUndrawMenu();}

}

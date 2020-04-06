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
    public void drawRectangle(Rectangle rectangle) {
        System.out.println("DrawRect in SimpleView !");
        this.implementation.devDrawRectangle(rectangle);
    }

    @Override
    public void drawPolygon(Polygon polygon) {
        this.implementation.devDrawPolygon(polygon);
    }
}

package application.view;

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
    public void drawRectangle() {
        this.implementation.devDrawRectangle();
    }

    @Override
    public void drawPolygon() {
        this.implementation.devDrawPolygon();
    }
}

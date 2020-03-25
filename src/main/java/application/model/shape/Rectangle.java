package application.model.shape;

public class Rectangle extends SingleShape {
    public Rectangle() {
        StaticUi.getInstance().addRectangleRepresentation(this);
    }
}

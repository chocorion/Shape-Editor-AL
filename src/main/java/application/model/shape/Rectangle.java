package application.model.shape;

import application.ui.StaticUi;

public class Rectangle extends SingleShape {
    public Rectangle() {
        StaticUi.getInstance().addRectangleRepresentation(this);
    }
}

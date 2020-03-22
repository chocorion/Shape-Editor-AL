package application.model.shape;

import application.ui.Ui;

public class Rectangle extends SingleShape {

    @Override
    public void draw() {
        Ui.getInstance().drawRectangle();
    }
}

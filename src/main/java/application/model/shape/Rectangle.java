package application.model.shape;

import application.view.View;

public class Rectangle extends SingleShape {
    public Rectangle() {

    }

    @Override
    public void draw(View view) {
        view.drawRectangle();
    }
}

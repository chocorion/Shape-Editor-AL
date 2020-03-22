package application.model.shape;

import application.ui.View;

public class Rectangle extends SingleShape {

    @Override
    public void draw(View view) {
        view.drawRectangle();
    }
}

package application.model.shape;

import application.view.View;

public class Polygon extends SingleShape {
    public Polygon() {

    }

    @Override
    public void draw(View view) {
        view.drawPolygon(this);
    }
}


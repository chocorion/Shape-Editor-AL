package application.view;

import application.model.shape.Polygon;
import application.model.shape.Rectangle;

public abstract class ViewDecorator implements View {
    private View view;

    public ViewDecorator(View view) {
        this.view = view;
    }

    @Override
    public void draw() {
        this.view.draw();
    }

    @Override
    public void drawRectangle(Rectangle rectangle) {
        this.view.drawRectangle(rectangle);
    }

    @Override
    public void drawPolygon(Polygon polygon) {
        this.view.drawPolygon(polygon);
    }
}

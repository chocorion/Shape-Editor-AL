package application.model.shape;

import application.view.View;

// TODO
public class Polygon extends SingleShape {
    public Polygon() {

    }

    @Override
    public void draw(View view) {
        view.drawPolygon(this);
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getMinX() {
        return 0;
    }

    @Override
    public int getMinY() {
        return 0;
    }

    @Override
    public int getMaxX() {
        return 0;
    }

    @Override
    public int getMaxY() {
        return 0;
    }

    @Override
    public boolean isIn(int x, int y) {
        return false;
    }

    @Override
    public boolean intersect(Rectangle rectangle) {
        return false;
    }

    @Override
    public void moveTo(int x, int y) {
        // TODO
    }

    @Override
    public void translate(int dx, int dy) {
        // TODO
    }

    @Override
    public void resize(double factor) {
        //TODO
    }

    @Override
    public void resize(Shape containerShape, double factor) {
        //TODO
    }
}


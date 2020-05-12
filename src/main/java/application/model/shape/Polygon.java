package application.model.shape;

import application.utils.Color;
import application.view.ViewBridge;

// TODO
public class Polygon extends SingleShape {
    private Color color;

    @Override
    public void draw(ViewBridge view) {

    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public double getMinX() {
        return 0;
    }

    @Override
    public double getMinY() {
        return 0;
    }

    @Override
    public double getMaxX() {
        return 0;
    }

    @Override
    public double getMaxY() {
        return 0;
    }

    @Override
    public boolean isIn(double x, double y) {
        return false;
    }

    @Override
    public boolean intersect(Rectangle rectangle) {
        return false;
    }

    @Override
    public void moveTo(double x, double y) {

    }

    @Override
    public void translate(double dx, double dy) {

    }

    @Override
    public void resize(double factor) {

    }

    @Override
    public void resize(Shape containerShape, double factor) {

    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}


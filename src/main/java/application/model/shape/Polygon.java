package application.model.shape;

import application.utils.Color;
import application.view.ViewBridge;


public class Polygon extends SingleShape {
    private Color color;
    private int numberSide;
    private double size;
    private int x, y;
    private double sideLenght;

    public Polygon(int x, int y, double size, int numberSide, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.numberSide = numberSide;

        this.color = color;
    }

    @Override
    public void draw(ViewBridge view) {

    }

    @Override
    public double getWidth() {
        return size;
    }

    @Override
    public double getHeight() {
        return size;
    }

    @Override
    public double getMinX() {
        return x;
    }

    @Override
    public double getMinY() {
        return y;
    }

    @Override
    public double getMaxX() {
        return x + size;
    }

    @Override
    public double getMaxY() {
        return y + size;
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
        this.x += x;
        this.y += y;
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

    @Override
    public Color getColor() {
        return this.color;
    }
}


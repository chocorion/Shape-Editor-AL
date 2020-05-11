package application.model.shape;

import application.utils.Color;
import application.view.ViewBridge;

public class Rectangle extends SingleShape {
    private double x, y;
    private double width, height;
    private Color color;

    public Rectangle(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        // For test
        this.color = color;
    }

    public Rectangle(double x, double y, double width, double height) {
        this(x, y, width, height, Color.BLACK);
    }




    @Override
    public void draw(ViewBridge view) {
        view.drawRectangle(this);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getMinX() {
        return this.x;
    }

    @Override
    public double getMinY() {
        return this.y;
    }

    @Override
    public double getMaxX() {
        return this.x + this.width;
    }

    @Override
    public double getMaxY() {
        return this.y + this.height;
    }

    @Override
    public boolean isIn(double x, double y) {
        if (x >= this.x && x <= this.x + this.width) {
            return y >= this.y && y <= this.y + this.height;
        }

        return false;
    }

    @Override
    public boolean intersect(Rectangle rect) {
        boolean hoverlap = (x < rect.x + rect.width) && (rect.x < x + width);
        boolean voverlap = (y < rect.y + rect.height) && (rect.y < y + height);

        return hoverlap && voverlap;
    }

    @Override
    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public void resize(double factor) {
        this.width *= factor;
        this.height *= factor;
    }

    @Override
    public void resize(Shape containerShape, double factor) {
        double minX = containerShape.getMinX();
        double minY = containerShape.getMinY();

        this.x = ((this.x - minX) * factor + minX);
        this.y = ((this.y - minY) * factor + minY);

        this.width *= factor;
        this.height *= factor;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String toString() {
        return "Rectangle(" + this.x + ", " + this.y + ", " + this.width + ", " + this.height + ")";
    }
}

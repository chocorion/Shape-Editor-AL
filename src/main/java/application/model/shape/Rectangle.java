package application.model.shape;

import application.utils.Color;
import application.view.View;

public class Rectangle extends SingleShape {
    private int x, y;
    private int width, height;
    private Color color;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        // For test
        this.color = Color.BLACK;
    }

    public Rectangle(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        // For test
        this.color = color;
    }


    @Override
    public void draw(View view) {
        view.drawRectangle(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getMinX() {
        return this.x;
    }

    @Override
    public int getMinY() {
        return this.y;
    }

    @Override
    public int getMaxX() {
        return this.x + this.width;
    }

    @Override
    public int getMaxY() {
        return this.y + this.height;
    }

    @Override
    public boolean isIn(int x, int y) {
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
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void translate(int dx, int dy) {
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
        int minX = containerShape.getMinX();
        int minY = containerShape.getMinY();

        this.x = (int) ((this.x - minX) * factor + minX);
        this.y = (int) ((this.y - minY) * factor + minY);

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

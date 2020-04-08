package application.model.shape;

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
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return this.color;
    }

    public String toString() {
        return "Rectangle(" + this.x + ", " + this.y + ", " + this.width + ", " + this.height + ");";
    }
}

package application.model.shape;

import application.view.View;

public class Rectangle extends SingleShape {
    private int x, y;
    private int width, height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

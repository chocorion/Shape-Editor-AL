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
        this.color = Color.BLUE;
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

    public Color getColor() {
        return this.color;
    }
}

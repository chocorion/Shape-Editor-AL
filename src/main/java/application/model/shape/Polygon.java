package application.model.shape;

import application.utils.Color;
import application.utils.Pair;
import application.view.ViewBridge;


public class Polygon extends SingleShape {
    private Color color;
    private int numberSide;
    private double size;
    private int x, y;
    private double sideLenght;
    private double angle;

    public Polygon(int x, int y, double size, int numberSide, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.numberSide = numberSide;

        this.angle = 0;
        this.color = color;
    }

    public Pair<double[], double[]> getPoints() {
        double[] xPoints = new double[numberSide];
        double[] yPoints = new double[numberSide];

        double angleDiff = 360./numberSide;
        double radius = size/2;

        for (int i = 0; i < numberSide; i++) {
            xPoints[i] = x + radius + radius * Math.cos(Math.toRadians(angle + i * angleDiff));
            yPoints[i] = y + radius + radius * Math.sin(Math.toRadians(angle + i * angleDiff));
        }

        return new Pair<>(xPoints, yPoints);
    }

    @Override
    public void draw(ViewBridge view) {
        view.drawPolygon(this);
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
        /*
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = points.length - 1; i < points.length; j = i++) {
            if ((points[i].y > test.y) != (points[j].y > test.y) &&
                    (test.x < (points[j].x - points[i].x) * (test.y - points[i].y) / (points[j].y-points[i].y) + points[i].x)) {
                result = !result;
            }
        }
        return result;*/

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


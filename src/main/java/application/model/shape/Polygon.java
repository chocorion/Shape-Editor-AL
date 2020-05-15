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
        Pair<double[], double[]> points = getPoints();

        double[] pointsX = points.getKey();
        double[] pointsY = points.getValue();

        int i;
        int j;
        boolean result = false;
        // assumes that pointx.length == pointy.lenght
        for (i = 0, j = pointsX.length - 1; i < pointsX.length; j = i++) {
            if ((pointsY[i] > y) != (pointsY[j] > y) &&
                    (x < (pointsX[j] - pointsX[i]) * (y - pointsY[i]) / (pointsY[j] - pointsY[i]) + pointsX[i])) {
                result = !result;
            }
        }

        return result;
    }

    @Override
    public boolean intersect(Rectangle rectangle) {
        if (isIn(rectangle.getX(), rectangle.getY()))
            return true;

        if (isIn(rectangle.getX() + rectangle.getWidth(), rectangle.getY()))
            return true;

        if (isIn(rectangle.getX(), rectangle.getY() + rectangle.getHeight()))
            return true;

        if (isIn(rectangle.getX() + rectangle.getWidth(), rectangle.getY() + rectangle.getHeight()))
            return true;

        Pair<double[], double[]> points = getPoints();

        double[] pointsX = points.getKey();
        double[] pointsY = points.getValue();

        for (int i = 0; i < pointsX.length; i++) {
            if (rectangle.isIn(pointsX[i], pointsY[i]))
                return true;
        }

        return false;
    }

    @Override
    public void moveTo(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    @Override
    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public void setAngle(double newAngle) {
        angle = newAngle;
    }

    @Override
    public void resize(double factor) {
        this.size *= factor;
    }

    @Override
    public void resize(Shape containerShape, double factor) {
        double minX = containerShape.getMinX();
        double minY = containerShape.getMinY();

        this.x = (int) ((this.x - minX) * factor + minX);
        this.y = (int) ((this.y - minY) * factor + minY);

        this.size *= factor;
    }

    public void setNumberSide(int newNumber) {
        if (newNumber > 2) numberSide = newNumber;
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


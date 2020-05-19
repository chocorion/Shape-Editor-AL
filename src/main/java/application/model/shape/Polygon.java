package application.model.shape;

import application.utils.Color;
import application.utils.Pair;
import application.view.ViewBridge;


/**
 * Represent a regular polygon in the model.
 */
public class Polygon extends SingleShape {
    private Color color;
    private int numberSide;
    private double size;
    private double x, y;
    private double angle;

    Pair<double[], double[]> points;


    /**
     * Parameterized constructor.
     * @param x The x top left coords.
     * @param y The Y top left coords.
     * @param size Radius of the outer polygon's circle.
     * @param numberSide Number of side of the polygon.
     * @param color Color of the polygon.
     */
    public Polygon(double x, double y, double size, int numberSide, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.numberSide = numberSide;

        this.angle = 0;
        this.color = color;

        points = computePoints();
    }


    /**
     * Return the position of each point of the polygon.
     * @return Pair with x positions in key, and y positions in value.
     */
    public Pair<double[], double[]> getPoints() {
        return this.points;
    }


    /**
     * Compute the position of each points, according to the current angle.
     * @return Pair with x positions in key, and y positions in value.
     */
    private Pair<double[], double[]> computePoints() {
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
        double[] p = points.getKey();
        double minX = p[0];

        for (int i = 1; i < p.length; i++) {
            if (p[i] < minX)
                minX = p[i];
        }

        return minX;
    }


    @Override
    public double getMinY() {
        double[] p = points.getValue();
        double minY = p[0];

        for (int i = 1; i < p.length; i++) {
            if (p[i] < minY)
                minY = p[i];
        }

        return minY;
    }


    @Override
    public double getMaxX() {
        double[] p = points.getKey();
        double maxX = p[0];

        for (int i = 1; i < p.length; i++) {
            if (p[i] > maxX)
                maxX = p[i];
        }

        return maxX;
    }


    @Override
    public double getMaxY() {
        double[] p = points.getValue();
        double maxY = p[0];

        for (int i = 1; i < p.length; i++) {
            if (p[i] > maxY)
                maxY = p[i];
        }

        return maxY;
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
        if (isIn(rectangle.getMinX(), rectangle.getMinY()))
            return true;

        if (isIn(rectangle.getMaxX(), rectangle.getMinY()))
            return true;

        if (isIn(rectangle.getMinX(), rectangle.getMaxY()))
            return true;

        if (isIn(rectangle.getMaxX(), rectangle.getMaxY()))
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

        points = computePoints();
    }


    @Override
    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;

        points = computePoints();
    }


    @Override
    public void setAngle(double newAngle) {
        angle = newAngle;
        points = computePoints();
    }


    @Override
    public void resize(double factor) {
        this.size *= factor;
        points = computePoints();
    }


    @Override
    public void resize(Shape containerShape, double factor) {
        double minX = containerShape.getMinX();
        double minY = containerShape.getMinY();

        this.x = (int) ((this.x - minX) * factor + minX);
        this.y = (int) ((this.y - minY) * factor + minY);

        this.size *= factor;
        points = computePoints();
    }

    public void setNumberSide(int newNumber) {
        if (newNumber > 2) {
            numberSide = newNumber;
            points = computePoints();
        }
    }


    @Override
    public void setColor(Color color) {
        this.color = color;
    }


    @Override
    public Color getColor() {
        return this.color;
    }


    @Override
    public String toString() {
        return "Polygon \n" + this.x + ", " + this.y + ", " + this.size + ", " + this.numberSide + ", " + this.angle+ ", "+
                +  (int)(255*this.color.getR())+", "+(int) (255* this.color.getG())+", "+(int) (255* this.color.getB())+", "+  this.color.getA()+"\n";
    }
    
}

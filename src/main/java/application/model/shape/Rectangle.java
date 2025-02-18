package application.model.shape;

import application.utils.Color;
import application.utils.Pair;
import application.view.ViewBridge;
import org.w3c.dom.css.Rect;


/**
 * Represent a rectangle in the model.
 */
public class Rectangle extends SingleShape {
    private double x, y;
    private double width, height;
    private Color color;

    private double angle;
    private double pointX[];
    private double pointY[];

    private double roundValue;


    /**
     * Parameterized constructor.
     * @param x X top left coords.
     * @param y Y top left coords.
     * @param width Width of the rectangle.
     * @param height Height of the rectangle.
     * @param color Color of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        pointX = new double[] {x, x + width, x + width, x};
        pointY = new double[] {y, y, y + height, y + height};

        this.angle =0;
        this.roundValue = 0.;
        this.color = color;
    }

    /**
     * Parameterized constructor, with default color.
     * @param x X top left coords.
     * @param y Y top left coords.
     * @param width Width of the rectangle.
     * @param height Height of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        this(x, y, width, height, Color.BLACK);
    }


    @Override
    public void draw(ViewBridge view) {
        view.drawRoundedRect(this);
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
        double minX = pointX[0];

        for (int i = 1; i < pointX.length; i++) {
            if (pointX[i] < minX)
                minX = pointX[i];
        }

        return minX;
    }


    @Override
    public double getMinY() {
        double minY = pointY[0];

        for (int i = 1; i < pointY.length; i++) {
            if (pointY[i] < minY)
                minY = pointY[i];
        }

        return minY;
    }


    @Override
    public double getMaxX() {
        double maxX = pointX[0];

        for (int i = 1; i < pointX.length; i++) {
            if (pointX[i] > maxX)
                maxX = pointX[i];
        }

        return maxX;
    }


    @Override
    public double getMaxY() {
        double maxY = pointY[0];

        for (int i = 1; i < pointY.length; i++) {
            if (pointY[i] > maxY)
                maxY = pointY[i];
        }

        return maxY;
    }

    /**
     * Update the position of each position of rectangle's points, according to the current angle.
     */
    private void computePoints() {
        double currentPx, currentPy;
        double rAngle = Math.toRadians(angle);

        pointX = new double[] {x, x + width, x + width, x};
        pointY = new double[] {y, y, y + height, y + height};

        for (int i = 0; i < pointX.length; i++) {
            currentPx = pointX[i] - (x + width/2);
            currentPy = pointY[i] - (y + height/2);

            pointX[i] = currentPx * Math.cos(rAngle) - currentPy * Math.sin(rAngle) + (x + width/2);
            pointY[i] = currentPx * Math.sin(rAngle) + currentPy * Math.cos(rAngle) + (y + height/2);
        }
    }


    /**
     * Return the round value.
     * @return Round value.
     */
    public double getRoundValue() {
        return roundValue;
    }


    /**
     * Set the round value.
     * @param newValue The new round value.
     */
    public void setRoundValue(double newValue) {
        this.roundValue = newValue;
    }


    @Override
    public boolean isIn(double x, double y) {
        int i;
        int j;
        boolean result = false;

        for (i = 0, j = pointX.length - 1; i < pointX.length; j = i++) {
            if ((pointY[i] > y) != (pointY[j] > y) &&
                    (x < (pointX[j] - pointX[i]) * (y - pointY[i]) / (pointY[j] - pointY[i]) + pointX[i])) {
                result = !result;
            }
        }

        return result;
    }


    @Override
    public boolean intersect(Rectangle rectangle) {
        if (isIn(rectangle.getMinX(), rectangle.getMinY()))
            return true;

        if (isIn(rectangle.getMinX() + rectangle.getWidth(), rectangle.getMinY()))
            return true;

        if (isIn(rectangle.getMinX(), rectangle.getMinY() + rectangle.getHeight()))
            return true;

        if (isIn(rectangle.getMinX() + rectangle.getWidth(), rectangle.getMinY() + rectangle.getHeight()))
            return true;


        for (int i = 0; i < pointX.length; i++) {
            if (rectangle.isIn(pointX[i], pointY[i]))
                return true;
        }

        return false;
    }


    @Override
    public void moveTo(double x, double y) {
        this.x = x;
        this.y = y;

        computePoints();
    }


    @Override
    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;

        computePoints();
    }


    @Override
    public void setAngle(double newAngle) {
        this.angle = newAngle;
        computePoints();
    }


    /**
     * Return the current angle of the shape, in degrees between 0 and 360.
     * @return Current angle.
     */
    public double getAngle() {
        return angle;
    }


    @Override
    public void resize(double factor) {
        this.width *= factor;
        this.height *= factor;
        this.roundValue *= factor;

        computePoints();
    }

    @Override
    public void resize(Shape containerShape, double factor) {
        double minX = containerShape.getMinX();
        double minY = containerShape.getMinY();

        this.x = ((this.x - minX) * factor + minX);
        this.y = ((this.y - minY) * factor + minY);

        this.width *= factor;
        this.height *= factor;
        this.roundValue *= factor;

        computePoints();
    }


    /**
     * Set the width.
     * @param newWidth New width value.
     */
    public void setWidth(double newWidth) {
        this.x += (width - newWidth)/2;
        this.width = newWidth;

        computePoints();
    }


    /**
     * Set the height.
     * @param newHeight New height value.
     */
    public void setHeight(double newHeight) {
        this.y += (height - newHeight)/2;
        this.height = newHeight;

        computePoints();
    }


    @Override
    public Color getColor() {
        return this.color;
    }


    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Rectangle \n" + this.x + ", " + this.y + ", " + this.width + ", " + this.height + ", "+ this.angle+ ", "+
                +  (int)(255*this.color.getR())+", "+(int) (255* this.color.getG())+", "+(int) (255* this.color.getB())+", "+  this.color.getA() + ", " + roundValue+"\n";
    }

    /**
     * Return if this rectangle is the same than an other one.
     * @param other The other rectangle.
     * @return true if it's the same rectangle, else false.
     */
    public boolean isEquals(Rectangle other) {
        return (
                other.x == this.x &&
                other.y == this.y &&
                other.width == this.width &&
                other.height == this.height &&
                other.angle == this.angle &&
                other.color == this.color
        );
    }
}

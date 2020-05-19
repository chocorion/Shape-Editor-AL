package application.utils;

/**
 * Represent an rgb color, with transparency.
 */
public class Color {
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color LIGHT_GREY = new Color(190, 190, 190);
    public static final Color BLUE = new Color(30, 30, 230);

    public static final Color SELECTION = new Color(30, 30, 150, 0.4);


    private int r, g, b;
    private double a;


    /**
     * Parameterized color
     * @param r Red value, between 0 and 255.
     * @param g Green value, between 0 and 255.
     * @param b Blue value, between 0 and 255.
     * @param a Transparency value, between 0. and 1.
     */
    public Color(int r, int g, int b, double a) {
        this.r = r;
        this.g = g;
        this.b = b;

        this.a = a;
    }


    /**
     * Parameterized construction, for non-transparent color.
     * @param r Red value, between 0 and 255.
     * @param g Green value, between 0 and 255.
     * @param b Blue value, between 0 and 255.
     */
    public Color(int r, int g, int b) {
        this(r, g, b, 1.);
    }


    /**
     * Return the red value, between 0. and 1.
     * @return Red value.
     */
    public double getR() {
        return this.r/255.;
    }


    /**
     * Return the blue value, between 0. and 1.
     * @return Blue value.
     */
    public double getB() {
        return this.b/255.;
    }


    /**
     * Return the green value, between 0. and 1.
     * @return Green value.
     */
    public double getG() {
        return this.g/255.;
    }


    /**
     * Return the transparency value, between 0. and 1.
     * @return Transparency value.
     */
    public double getA() {
        return this.a;
    }


    /**
     * Tests if this color is equals to another one.
     * @param c The other color.
     * @return True if it's the same color, else false.
     */
    public boolean isEquals(Color c) {
        return (
                this.r == c.r &&
                this.g == c.g &&
                this.b == c.b &&
                this.a == c.a
        );
    }
}

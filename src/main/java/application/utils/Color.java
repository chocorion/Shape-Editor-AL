package application.utils;

public class Color {
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color LIGHT_GREY = new Color(190, 190, 190);
    public static final Color BLUE = new Color(30, 30, 230);


    private int r, g, b;
    private double a;

    public Color(int r, int g, int b, double a) {
        this.r = r;
        this.g = g;
        this.b = b;

        this.a = a;
    }

    public Color(int r, int g, int b) {
        this(r, g, b, 1.);
    }

    public double getR() {
        return this.r/255.;
    }

    public double getB() {
        return this.b/255.;
    }

    public double getG() {
        return this.g/255.;
    }

    public double getA() {
        return this.a;
    }
}

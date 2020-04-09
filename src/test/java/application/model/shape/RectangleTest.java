package application.model.shape;

import org.junit.Test;

import static org.junit.Assert.*;

public class RectangleTest {

    @Test
    public void isIn() {
        Rectangle rect = new Rectangle(2, 3, 5, 5);

        assert (rect.isIn(2, 3));
        assert (rect.isIn(7, 8));
        assert (rect.isIn(4, 4));
        assert (!rect.isIn(1, 2));
        assert (!rect.isIn(9, 9));
    }

    @Test
    public void resizeWithFactor() {
        Rectangle rect = new Rectangle(2, 2, 6, 6);
        rect.resize(0.5);

        assert (rect.getX() == 2 && rect.getY() == 2);
        assert (rect.getWidth() == 3 && rect.getHeight() == 3);
    }

    @Test
    public void translate() {
        Rectangle r = new Rectangle(10, 20, 30, 40);
        r.translate(20, 10);

        assert (r.getX() == 30 && r.getY() == 30);
    }
}
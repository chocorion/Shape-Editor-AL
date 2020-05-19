package application.model.shape;

import org.junit.Test;

import static org.junit.Assert.*;

public class RectangleTest {

    @Test
    public void isIn() {
        Rectangle rect = new Rectangle(2, 3, 5, 5);

        assert (rect.isIn(2, 3));
        assert (rect.isIn(6.9, 7.9));
        assert (rect.isIn(4, 4));
        assert (!rect.isIn(1, 2));
        assert (!rect.isIn(9, 9));
    }

    @Test
    public void resizeWithFactor() {
        Rectangle rect = new Rectangle(2, 2, 6, 6);
        rect.resize(0.5);

        assert (rect.getMinX() == 2 && rect.getMinY() == 2);
        assert (rect.getWidth() == 3 && rect.getHeight() == 3);
    }

    @Test
    public void translate() {
        Rectangle r = new Rectangle(10, 20, 30, 40);
        r.translate(20, 10);

        assert (r.getMinX() == 30 && r.getMinY() == 30);
    }
}
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
}
package application.model.shape;

import application.utils.Color;
import org.junit.Test;

public class PolygonTest {

    @Test
    public void testIsIn() {
        Polygon p = new Polygon(5, 5, 50, 3, Color.WHITE);

        assert (p.isIn(25, 25));
        assert (!p.isIn(5, 5));
        assert (!p.isIn(55, 55));
    }

    @Test
    public void testIntersect() {
        Polygon p = new Polygon(5, 5, 50, 3, Color.WHITE);
        Rectangle r = new Rectangle(2, 2, 3, 3);

        assert (!p.intersect(r));

        r.setWidth(100);
        r.setHeight(100);
        assert (p.intersect(r));

        assert (new Polygon(-500, -500, 1000, 5, Color.BLACK).intersect(r));
    }
}
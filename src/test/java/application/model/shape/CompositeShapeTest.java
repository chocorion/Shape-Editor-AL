package application.model.shape;

import org.junit.Test;

import static org.junit.Assert.*;

public class CompositeShapeTest {

    @Test
    public void getWidth() {
        CompositeShape shape = new CompositeShape();
        shape.add(new Rectangle(0, 0, 10, 1));
        shape.add(new Rectangle(10, 10, 10, 1));

        assert (shape.getWidth() == 20);
    }

    @Test
    public void getHeight() {
        CompositeShape shape = new CompositeShape();
        shape.add(new Rectangle(0, 0, 1, 10));
        shape.add(new Rectangle(0, 10, 1, 10));

        assert (shape.getHeight() == 20);
    }

    @Test
    public void getMinX() {
        CompositeShape shape = new CompositeShape();
        shape.add(new Rectangle(0, 0, 1, 10));
        shape.add(new Rectangle(10, 10, 1, 10));
        shape.add(new Rectangle(142, 10, 1, 10));

        assert (shape.getMinX() == 0);
    }

    @Test
    public void getMaxX() {
        CompositeShape shape = new CompositeShape();
        shape.add(new Rectangle(0, 0, 1, 10));
        shape.add(new Rectangle(10, 10, 1, 10));
        shape.add(new Rectangle(142, 10, 10, 10));

        assert (shape.getMaxX() == 152);
    }

    @Test
    public void getMinY() {
        CompositeShape shape = new CompositeShape();
        shape.add(new Rectangle(0, 0, 1, 10));
        shape.add(new Rectangle(10, 10, 1, 10));
        shape.add(new Rectangle(142, 200, 1, 10));

        assert (shape.getMinY() == 0);
    }

    @Test
    public void getMaxY() {CompositeShape shape = new CompositeShape();
        shape.add(new Rectangle(0, 0, 1, 10));
        shape.add(new Rectangle(10, 10, 1, 10));
        shape.add(new Rectangle(142, 200, 1, 10));

        assert (shape.getMaxY() == 210);

    }
}
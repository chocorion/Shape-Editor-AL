package application.model.shape;

import application.exceptions.UnsupportedOperationException;
import org.junit.Test;

import static org.junit.Assert.*;

public class SingleShapeTest {

    @Test(expected = UnsupportedOperationException.class)
    public void add() throws UnsupportedOperationException {
        SingleShape shape = new Rectangle(0, 0, 1, 1);

        shape.add(new Rectangle(0, 0, 1, 1));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void remove() throws UnsupportedOperationException {
        SingleShape shape = new Rectangle(0, 0, 1, 1);

        shape.remove(new Rectangle(0, 0, 1, 1));
    }
}
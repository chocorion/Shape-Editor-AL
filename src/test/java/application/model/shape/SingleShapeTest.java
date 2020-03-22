package application.model.shape;

import application.exceptions.UnsupportedOperationException;
import org.junit.Test;

import static org.junit.Assert.*;

public class SingleShapeTest {

    @Test(expected = UnsupportedOperationException.class)
    public void add() throws UnsupportedOperationException {
        SingleShape shape = new Rectangle();

        shape.add(new Rectangle());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void remove() throws UnsupportedOperationException {
        SingleShape shape = new Rectangle();

        shape.remove(new Rectangle());
    }
}
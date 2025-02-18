package application.model.shape;

import application.utils.Color;
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
    public void getMaxY() {
        CompositeShape shape = new CompositeShape();
        shape.add(new Rectangle(0, 0, 1, 10));
        shape.add(new Rectangle(10, 10, 1, 10));
        shape.add(new Rectangle(142, 200, 1, 10));

        assert (shape.getMaxY() == 210);

    }

    @Test
    public void isIn() {
        CompositeShape shape = new CompositeShape();

        shape.add(new Rectangle(0, 0, 5, 5));
        shape.add(new Rectangle(3, 3, 8, 8));
        shape.add(new Rectangle(9, 9, 2, 2));

        assert (shape.isIn(0, 0));
        assert (shape.isIn(4, 4));
        assert (!shape.isIn(12, 12));
        assert (shape.isIn(9, 9));
    }

    @Test
    public void resize() {
        CompositeShape shape = new CompositeShape();

        Rectangle rect1 = new Rectangle(10, 10, 30, 30);
        Rectangle rect2 = new Rectangle(40, 40, 20, 20);

        shape.add(rect1);
        shape.add(rect2);

        shape.resize(0.5);

        assert (rect1.getMinX() == 10 && rect1.getMinY() == 10);
        assert (rect1.getWidth() == 15 && rect1.getHeight() == 15);

        assert (rect2.getMinX() == 25 && rect2.getMinY() == 25);
        assert (rect2.getWidth() == 10 && rect2.getHeight() == 10);

        assert (shape.getMinX() == 10 && shape.getMinY() == 10);
        assert (shape.getMaxX() == 35 && shape.getMaxY() == 35);

        assert (shape.getWidth() == 25 && shape.getHeight() == 25);
    }

    @Test
    public void moveTo() {
        CompositeShape compositeShape = new CompositeShape();
        Rectangle rect1 = new Rectangle(0, 0, 50, 50, new Color(180, 100, 100));
        Rectangle rect2 = new Rectangle(50, 50, 50, 50, new Color(180, 100, 100));

        compositeShape.add(rect1);
        compositeShape.add(rect2);
        compositeShape.moveTo(181, 213);

        assert (rect1.getMinX() == 181 && rect1.getMinY() == 213);
        assert (rect2.getMinX() == 231 && rect2.getMinY() == 263);
    }

    @Test
    public void moveToMultiplesComposites() {
        CompositeShape compositeShape1 = new CompositeShape();
        Rectangle rect1 = new Rectangle(0, 0, 50, 50, new Color(180, 100, 100));
        Rectangle rect2 = new Rectangle(50, 50, 50, 50, new Color(180, 100, 100));

        compositeShape1.add(rect1);
        compositeShape1.add(rect2);

        CompositeShape compositeShape2 = new CompositeShape();
        Rectangle rect3 = new Rectangle(50, 50, 50, 50, new Color(180, 100, 100));
        Rectangle rect4 = new Rectangle(100, 100, 50, 50, new Color(180, 100, 100));

        compositeShape2.add(rect3);
        compositeShape2.add(rect4);


        CompositeShape compositeShape = new CompositeShape();
        compositeShape.add(compositeShape1);
        compositeShape.add(compositeShape2);

        int width = (int) compositeShape.getWidth();
        int height = (int) compositeShape.getHeight();

        compositeShape.resize(0.5);
        assert (compositeShape.getHeight() == height/2 && compositeShape.getWidth() == width/2);



    }
}
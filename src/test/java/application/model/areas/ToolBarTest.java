package application.model.areas;

import application.model.Model;
import application.model.shape.Rectangle;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToolBarTest {

    @Test
    public void getShape() {
        Model model = new Model();
        ToolBar toolBar = new ToolBar(model);

        Rectangle rect1 = new Rectangle(0, 0, 10, 10);
        Rectangle rect2 = new Rectangle(10, 10, 40, 40);

        // Already some defauls shapes in toolbar
        int numberShape = toolBar.getInnerShapes().size();

        toolBar.addShape(rect1);
        toolBar.addShape(rect2);

        Rectangle rect = (Rectangle) toolBar.getShape(numberShape);

        assert (rect != rect1 && rect != rect2 && rect != null);
        assert (rect.getX() == rect1.getX() && rect.getY() == rect1.getY());

        // 40 is in the second case
        rect = (Rectangle) toolBar.getShape(numberShape + 1);

        assert (rect != rect1 && rect != rect2 && rect != null);
        //System.err.println(rect);
        assert (rect.getX() == rect2.getX() && rect.getY() == rect2.getY());

    }
}
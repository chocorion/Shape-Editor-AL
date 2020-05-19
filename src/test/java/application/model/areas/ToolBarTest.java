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
        assert (rect.isEquals(rect1));

        rect = (Rectangle) toolBar.getShape(numberShape + 1);
        assert (rect.isEquals(rect2));
    }
}
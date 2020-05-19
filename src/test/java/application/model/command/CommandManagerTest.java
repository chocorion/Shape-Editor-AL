package application.model.command;

import application.model.Model;
import application.model.areas.ShapeContainer;
import application.model.areas.WhiteBoard;
import application.model.command.concreteCommand.AddShape;
import application.model.command.concreteCommand.MoveShape;
import application.model.shape.Rectangle;
import application.model.shape.Shape;
import application.utils.Color;
import junit.framework.TestCase;
import org.junit.Test;

public class CommandManagerTest {
    @Test
    public void testSimpleUndo() {
        // Check if a simple undo works.
        Model model = new Model();
        ShapeContainer container = new WhiteBoard(model);
        Shape s = new Rectangle(0, 0, 1, 1);
        Command cmd = new AddShape(container, s);

        assert (container.getShapePlace(s) == -1);
        model.execute(cmd);
        assert (container.getShapePlace(s) != -1);
        model.undo();
        assert (container.getShapePlace(s) == -1);
    }

    @Test
    public void testSimpleRedo() {
        // Check if a simple redo works.
        Model model = new Model();
        ShapeContainer container = new WhiteBoard(model);
        Shape s = new Rectangle(0, 0, 1, 1);
        Command cmd = new AddShape(container, s);

        assert (container.getShapePlace(s) == -1);
        model.execute(cmd);
        assert (container.getShapePlace(s) != -1);
        model.undo();
        assert (container.getShapePlace(s) == -1);
        model.redo();
        assert (container.getShapePlace(s) != -1);
    }


    @Test
    public void testMultipleUndo() {
        // Check if undo works with multiples cmd
        Model model = new Model();
        ShapeContainer container = new WhiteBoard(model);

        Shape s = new Rectangle(0, 0, 1, 1);
        Shape s2 = new Rectangle(0, 0, 2, 2);

        Command cmd = new AddShape(container, s);
        Command cmd2 = new AddShape(container, s2);

        assert (container.getShapePlace(s) == -1);
        assert (container.getShapePlace(s2) == -1);

        model.execute(cmd);
        assert (container.getShapePlace(s) != -1);
        assert (container.getShapePlace(s2) == -1);

        model.execute(cmd2);
        assert (container.getShapePlace(s) != -1);
        assert (container.getShapePlace(s2) != -1);

        model.undo();
        assert (container.getShapePlace(s) != -1);
        assert (container.getShapePlace(s2) == -1);

        model.undo();
        assert (container.getShapePlace(s) == -1);
        assert (container.getShapePlace(s2) == -1);
    }

    @Test
    public void testMultipleImpossibleRedo() {
        // Assure that we cannot make multiples redo of the same cmd
        Model model = new Model();
        ShapeContainer container = new WhiteBoard(model);

        Shape s = new Rectangle(0, 0, 1, 1);

        Command cmd = new AddShape(container, s);

        model.execute(cmd);
        assert (container.getShapePlace(s) != -1);
        model.undo();
        assert (container.getShapePlace(s) == -1);
        model.redo();
        assert (container.getShapePlace(s) != -1);

        container.removeShape(s);
        model.redo();
        assert (container.getShapePlace(s) == -1);
    }

    @Test
    public void testMultipleImpossibleUndo() {
        // Assure that we cannot make multiples undo of the same cmd
        Model model = new Model();
        Rectangle r = new Rectangle(0, 0, 1, 1, Color.WHITE);
        WhiteBoard w = new WhiteBoard(model);

        Command cmd = new MoveShape(w, r, 50, 50);
        model.execute(cmd);

        assert (r.getMinX() == 50 && r.getMinY() == 50);
        model.undo();
        assert (r.getMinX() == 0 && r.getMinY() == 0);

        r.moveTo(50, 50);
        model.undo();
        assert (r.getMinX() == 50 && r.getMinY() == 50);
    }
}
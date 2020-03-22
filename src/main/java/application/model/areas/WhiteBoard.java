package application.model.areas;

import application.model.shape.CompositeShape;
import application.model.shape.Shape;

public class WhiteBoard {
    private Shape rootShape;

    public WhiteBoard() {
        this.rootShape = new CompositeShape();
    }
}

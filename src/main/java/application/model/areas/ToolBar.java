package application.model.areas;

import application.model.shape.CompositeShape;
import application.model.shape.Shape;

public class ToolBar {
    private Shape rootShape;

    public ToolBar() {
        this.rootShape = new CompositeShape();
    }
}

package application.model.areas;

import application.model.shape.CompositeShape;
import application.model.shape.Shape;
import application.utils.ModelObservableImp;

public class ToolBar extends ModelObservableImp {
    private Shape rootShape;

    public ToolBar() {
        this.rootShape = new CompositeShape();
    }
}

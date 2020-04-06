package application.model.areas;

import application.model.shape.CompositeShape;
import application.model.shape.Shape;
import application.utils.ModelObservableImp;

public class WhiteBoard extends ModelObservableImp {
    private Shape rootShape;

    public WhiteBoard() {
        this.rootShape = new CompositeShape();
    }

    public void update() {
        super.notifyObserver();
    }
}

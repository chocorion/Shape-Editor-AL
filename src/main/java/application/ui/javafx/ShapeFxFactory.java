package application.ui.javafx;

import application.ui.GraphicalShape;
import application.ui.ShapeFactory;

public class ShapeFxFactory implements ShapeFactory {
    @Override
    public GraphicalShape getRectangle() {
        return new RectangleFx();
    }
}

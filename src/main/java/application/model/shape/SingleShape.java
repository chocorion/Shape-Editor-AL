package application.model.shape;

import application.model.utils.ObservableImp;
import application.model.utils.Point;
import application.exceptions.UnsupportedOperationException;

public abstract class SingleShape extends ObservableImp implements Shape, Cloneable {
    private Point position;

    @Override
    public void add(Shape shape) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't call add on single shape.");
    }

    @Override
    public void remove(Shape shape) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't call remove on single shape.");
    }

    // Penser, lors du clone, à changer les observers !
    @Override
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch(CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }

        return o;
    }
}

package application.model.shape;

import application.exceptions.UnsupportedOperationException;

public abstract class SingleShape implements Shape, Cloneable {
    @Override
    public void add(Shape shape) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't call add on single shape.");
    }

    @Override
    public void remove(Shape shape) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Can't call remove on single shape.");
    }

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

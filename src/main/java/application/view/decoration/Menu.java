package application.view.decoration;

import application.utils.ModelObservable;
import application.view.ObserverDecoration;
import application.view.ViewDecorator;

public abstract class Menu extends ViewDecorator implements ObserverDecoration {
    ObserverDecoration decoration;
    ModelObservable subject;

    public Menu(ObserverDecoration decoration) {
        super(decoration);

        this.decoration = decoration;
        this.subject = null;
    }

    public void open() {
        decoration.getSubject().dettachObserver(decoration);
        decoration.getSubject().attachObserver(this);

        this.update();
    }

    public void close() {
        subject.dettachObserver(this);
        subject.attachObserver(decoration);

        decoration.update();
    }

    @Override
    public ModelObservable getSubject() {
        return subject;
    }
}

package application.view.decoration;

import application.utils.ModelObservable;
import application.view.ObserverDecoration;
import application.view.ViewDecorator;

public abstract class Menu extends ViewDecorator implements ObserverDecoration {
    ObserverDecoration decoration;
    ModelObservable subject;
    int x, y;
    private boolean toggle;

    public Menu(ObserverDecoration decoration) {
        super(decoration);

        this.decoration = decoration;
        this.subject = null;
        toggle = false;
    }

    public void open(int x, int y) {
        toggle = true;

        this.x = x;
        this.y = y;

        decoration.getSubject().dettachObserver(decoration);
        decoration.getSubject().attachObserver(this);

        this.update();
    }

    public void close() {
        toggle = false;

        subject.dettachObserver(this);
        subject.attachObserver(decoration);

        decoration.update();
    }

    @Override
    public ModelObservable getSubject() {
        return subject;
    }

    public boolean isToggle() {
        return toggle;
    }
}

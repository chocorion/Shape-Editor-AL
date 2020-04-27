package application.view;

import application.utils.ModelObservable;
import application.utils.ModelObserver;

public interface ObserverDecoration extends ModelObserver, View {
    ModelObservable getSubject();
}

package application.ui.javafx;

import application.controller.ConcreteControllerItf;
import application.controller.MainController;
import application.model.Model;
import application.ui.UIFactory;
import application.view.ConcreteViewItf;

public class UIFx implements UIFactory {

    @Override
    public ConcreteViewItf getView(Model model) {
        return new ViewFx(model);
    }

    @Override
    public ConcreteControllerItf getController(MainController mainController, ConcreteViewItf view) {
        assert view instanceof ViewFx;

        return new ControllerFx(mainController, (ViewFx) view);
    }
}

package application.ui.javafx;

import application.controller.IConcreteController;
import application.controller.MainController;
import application.model.Model;
import application.ui.UIFactory;
import application.view.IConcreteView;

public class UIFx implements UIFactory {

    @Override
    public IConcreteView getView(Model model) {
        return new ViewFx(model);
    }

    @Override
    public IConcreteController getController(MainController mainController, IConcreteView view) {
        assert view instanceof ViewFx;

        return new ControllerFx(mainController, (ViewFx) view);
    }
}

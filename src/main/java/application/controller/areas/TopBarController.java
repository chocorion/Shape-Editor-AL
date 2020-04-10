package application.controller.areas;

import application.controller.MainController;
import application.model.areas.TopBar;
import application.view.areas.TopBarView;

public class TopBarController {
    private TopBar model;
    private TopBarView view;
    private MainController mainController;

    public TopBarController(MainController mainController, TopBar model, TopBarView view) {
        this.mainController = mainController;

        this.model = model;
        this.view = view;
    }

    public void onLeftClickPressed(int x, int y) {
        int buttonId = view.getButtonId(x, y);

        if (buttonId != -1)
            model.clickOnButton(buttonId);
    }

}

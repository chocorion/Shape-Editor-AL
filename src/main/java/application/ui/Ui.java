package application.ui;

import application.model.Model;

public class Ui {

    private View view;
    private Controller controller;

    public Ui(Model model) {
        view = new ViewFX(model);
        controller = new ControllerFX();
    }

    public void tick() {
        this.controller.tick();
        this.view.tick();
    }

    /**
     * TODO
     */
    public void drawRectangle() {
        view.drawRectangle();
    }
}

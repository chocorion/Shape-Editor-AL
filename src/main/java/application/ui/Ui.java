package application.ui;

public class Ui {
    private static Ui instance;

    private static View view;
    private static Controller controller;

    private Ui() {
        view = new ViewFX();
        controller = new ControllerFX();
    }

    public static Ui getInstance() {
        if (instance == null) {
            instance = new Ui();
        }

        return instance;
    }

    /**
     * TODO
     */
    public void drawRectangle() {
        view.drawRectangle();
    }
}

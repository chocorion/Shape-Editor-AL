package application.ui;

import application.exceptions.InvalidCallException;

public class StaticUi {
    private static Ui instance;

    private StaticUi() {}

    public static void setInstance(Ui ui) {
        instance = ui;
    }

    public static Ui getInstance() {
        if (instance == null) {
            System.err.println("Fatal Error : Static Ui was not initialized !");
            System.exit(1);
        }

        return instance;
    }
}

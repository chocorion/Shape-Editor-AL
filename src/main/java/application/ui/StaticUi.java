package application.ui;

import application.exceptions.InvalidCallException;

public class StaticUi {
    private static Ui instance;

    private StaticUi() {}

    public static void setInstance(Ui ui) {
        instance = ui;
    }

    public static Ui getInstance() throws InvalidCallException {
        if (instance ==null) {
            throw new InvalidCallException("The object has not been initialized.");
        }

        return instance;
    }
}

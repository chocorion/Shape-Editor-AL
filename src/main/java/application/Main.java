package application;

import application.model.Model;
import application.ui.StaticUi;
import application.ui.Ui;
import application.ui.javafx.UiFx;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world !");

        Model model = new Model();
        Ui ui = new UiFx(model);
        StaticUi.setInstance(ui);

        ui.start();
    }
}

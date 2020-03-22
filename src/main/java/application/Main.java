package application;

import application.model.Model;
import application.ui.Ui;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world !");

        Model model = new Model();
        Ui ui = new Ui(model);

        while (true) {
            ui.tick();
        }
    }
}

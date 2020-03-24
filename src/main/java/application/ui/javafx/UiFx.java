package application.ui.javafx;

import application.model.Model;
import application.model.shape.SingleShape;
import application.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class UiFx extends Application implements Ui {
    private Model model;

    /**
     * Needed by javaFx for Application.launch.
     */
    public UiFx() {}

    public UiFx(Model model) {
        this.model = model;
    }

    @Override
    public void start() {
        Application.launch(UiFx.class, (String[]) null);
    }

    @Override
    public void addRectangleRepresentation(SingleShape rectangle) {
        RectangleFx rectangleFx = new RectangleFx();

        rectangle.attachObserver(rectangleFx);
    }

    @Override
    public void start(final Stage primaryStage) {
        try {
            URL url = getClass().getClassLoader().getResource("views/mainView.fxml");
            System.out.println("Url -> " + url);

            final FXMLLoader fxmlLoader = new FXMLLoader(url);

            final AnchorPane root = (AnchorPane) fxmlLoader.load();

            final Scene scene = new Scene(root, 300, 250);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Shape Editor");
        primaryStage.show();
    }

}

package application.ui.javafx;

import application.model.Model;
import application.model.shape.SingleShape;
import application.ui.Ui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


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
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250, Color.LIGHTGREEN);
        Button btn = new Button();
        btn.setLayoutX(100);
        btn.setLayoutY(80);
        btn.setText("Hello World");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                System.out.println("Hello World");
            }
        });
        root.getChildren().add(btn);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

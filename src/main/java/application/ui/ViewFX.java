package application.ui;

import application.model.Model;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class ViewFX implements View {
    private Model model;

    public ViewFX(Model model) {
        this.model = model;

        Application.launch(myApplication.class, null);
    }

    public static class myApplication extends Application {
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


    @Override
    public void tick() {

    }

    @Override
    public void drawRectangle() {

    }
}

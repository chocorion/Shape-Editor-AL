package application.ui.javafx;


import application.controller.ControllerFx;
import application.model.Model;
import application.model.shape.Polygon;
import application.model.shape.Rectangle;
import application.view.ConcreteViewItf;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import javax.naming.ldap.Control;


public class ViewFx extends Application implements ConcreteViewItf {
    private GraphicsContext gc;
    private ControllerFx controller;

    public ViewFx() {}

    public void initViewFx () {
        Application.launch(ViewFx.class, (String[]) null);
    }


    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Shape Editor");

        Group root = new Group();

        Canvas canvas = new Canvas(Model.WIDTH, Model.HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        Scene rootScene = new Scene(root);
        this.controller = new ControllerFx(rootScene);
        primaryStage.setScene(rootScene);


        new AnimationTimer(){
            long prevNanoTime = System.nanoTime();
            public void handle(long currentNanoTime){
                // double delta = (currentNanoTime - prevNanoTime) / 1000000.;
                // if (!sceneManager.tick(delta)) {
                //     System.exit(0);
                // }
                controller.tick();
                // prevNanoTime = currentNanoTime;
            }
        }.start();

        primaryStage.show();
    }

    @Override
    public void devDrawRectangle(Rectangle rectangle) {
        System.out.println("Drawing rectangle !");
        this.gc.fillRect(50, 50, 100, 150);
    }

    @Override
    public void devDrawPolygon(Polygon polygon) {
        this.gc.fillRect(50, 50, 100, 150);
    }
}

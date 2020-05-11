package application.ui.javafx;


import application.controller.Controller;
import application.model.Model;
import application.model.shape.Polygon;
import application.model.shape.Rectangle;
import application.view.ConcreteViewItf;
import application.view.areas.Layout;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ViewFx extends Application implements ConcreteViewItf {
    private static Model model;
    private static Scene rootScene;
    private static GraphicsContext gc;

    private static HashMap<String, Image> imageCache = new HashMap<>();

    public ViewFx() {
    }


    public ViewFx(Model model) {
        ViewFx.model = model;

        Group root = new Group();

        Canvas canvas = new Canvas(10000, 10000);
        ViewFx.gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        ViewFx.rootScene = new Scene(root);
    }

    public void initViewFx () {
        Application.launch(ViewFx.class, (String[]) null);
    }

    public Scene getScene() {
        return ViewFx.rootScene;
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setMinWidth(480);
        primaryStage.setMinHeight(480);
        primaryStage.setWidth(500);
        primaryStage.setHeight(600);

        primaryStage.setTitle("Shape Editor");
        primaryStage.setScene(ViewFx.rootScene);
        primaryStage.show();
        model.update();
    }

    public void AddController(Controller controller) {
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
    }


    @Override
    public int getWidth() {
        return (int) Screen.getPrimary().getVisualBounds().getWidth();
    }

    @Override
    public int getHeight() {
        return (int) Screen.getPrimary().getVisualBounds().getHeight();
    }

    @Override
    public void devDrawRectangle(Rectangle rectangle) {
        application.utils.Color rectColor = rectangle.getColor();
        ViewFx.gc.setFill(new Color(rectColor.getR(), rectColor.getG(), rectColor.getB(), rectColor.getA()));

        ViewFx.gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    @Override
    public void devDrawStrokeRectangle(Rectangle rectangle) {
        application.utils.Color rectColor = rectangle.getColor();
        ViewFx.gc.setStroke(new Color(rectColor.getR(), rectColor.getG(), rectColor.getB(), rectColor.getA()));

        ViewFx.gc.strokeRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    @Override
    public void devDrawPolygon(Polygon polygon) {
        ViewFx.gc.fillRect(50, 50, 100, 150);
    }


    @Override
    public void devDrawImage(String path, int x, int y, int width, int height) {
        if (!imageCache.containsKey(path)) {
            imageCache.put(path, new Image(path));
        }

        ViewFx.gc.drawImage(imageCache.get(path), x, y, width, height);
    }

    @Override
    public void devDrawText(String text, int x, int y, int size, application.utils.Color color) {
        ViewFx.gc.setFill(new Color(color.getR(), color.getG(), color.getB(), color.getA()));
        ViewFx.gc.fillText(text, x, y, size);
    }


}

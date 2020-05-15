package application.ui.javafx;


import application.controller.ConcreteControllerItf;
import application.model.Model;
import application.model.shape.Polygon;

import application.utils.Pair;
import application.view.ConcreteViewItf;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;

import javafx.scene.paint.Color;

import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Optional;


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

    public void AddController(ConcreteControllerItf controller) {
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
    public void devDrawRectangle(int x, int y, int width, int height, application.utils.Color color) {
        ViewFx.gc.setFill(new Color(color.getR(), color.getG(), color.getB(), color.getA()));

        ViewFx.gc.fillRect(x, y, width, height);
    }

    @Override
    public void devDrawStrokeRectangle(int x, int y, int width, int height, application.utils.Color color) {
        ViewFx.gc.setStroke(new Color(color.getR(), color.getG(), color.getB(), color.getA()));

        ViewFx.gc.strokeRect(x, y, width, height);
    }

    @Override
    public void devDrawPolygon(double[] x, double[] y, application.utils.Color color) {
        ViewFx.gc.setFill(new Color(color.getR(), color.getG(), color.getB(), color.getA()));

        ViewFx.gc.fillPolygon(x, y, x.length);
    }


    @Override
    public void devDrawImage(String path, int x, int y, int width, int height) {
        if (!imageCache.containsKey(path)) {
            imageCache.put(path, new Image(path));
        }

        ViewFx.gc.drawImage(imageCache.get(path), x, y, width, height);
    }

    @Override
    public void devDrawRoundedRect(int x, int y, int width, int height, int arcWidth, int arcHeight, application.utils.Color color) {
        ViewFx.gc.setFill(new Color(color.getR(), color.getG(), color.getB(), color.getA()));
        ViewFx.gc.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    @Override
    public void devDrawText(String text, int x, int y, int size, application.utils.Color color) {
        ViewFx.gc.setFill(new Color(color.getR(), color.getG(), color.getB(), color.getA()));
        ViewFx.gc.fillText(text, x, y, size);
    }

    @Override
    public void devDrawSaveMenu() {
        TextInputDialog dialog = new TextInputDialog("path ...");
        dialog.setTitle("Saving");
        dialog.setHeaderText("Saving Path Output");
        dialog.setContentText("Please enter a valid path:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            this.model.getWhiteBoard().save(result.get());
        }
    }

    @Override
    public void devDrawLoadMenu() {
        TextInputDialog dialog = new TextInputDialog("path ...");
        dialog.setTitle("Loading");
        dialog.setHeaderText("Loading Path Input");
        dialog.setContentText("Please enter a valid path:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            this.model.getWhiteBoard().load(result.get());
        }
    }


}

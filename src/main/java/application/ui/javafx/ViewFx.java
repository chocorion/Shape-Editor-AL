package application.ui.javafx;


import application.controller.IConcreteController;
import application.model.Model;

import application.view.IConcreteView;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;

import javafx.scene.paint.Color;

import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Optional;


/**
 * JavaFx implementation of the view.
 */
public class ViewFx extends Application implements IConcreteView {
    private static Model model;
    private static Scene rootScene;
    private static GraphicsContext gc;

    private static HashMap<String, Image> imageCache = new HashMap<>();


    /**
     * Default constructor, needed for launching javaFx.
     */
    public ViewFx() {
    }


    /**
     * Parameterized constructor used to build the ui.
     * @param model Model to use.
     */
    public ViewFx(Model model) {
        ViewFx.model = model;

        Group root = new Group();

        Canvas canvas = new Canvas(10000, 10000);
        ViewFx.gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        ViewFx.rootScene = new Scene(root);
    }


    /**
     * Start the javaFx application
     */
    public void initViewFx () {
        Application.launch(ViewFx.class, (String[]) null);
    }


    /**
     * Return the current rootScene, needed to add controllerFx.
     * @return Current root scene.
     */
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
            try {
                imageCache.put(path, new Image(String.valueOf(Paths.get(path).toUri().toURL())));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
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


    @Override
    public void devDrawRoundedRectRotation(int x, int y, int width, int height, int arcWidth, int arcHeight, int angle, application.utils.Color color) {
        ViewFx.gc.save();
        ViewFx.gc.transform(new Affine(new Rotate(angle, x + width/2, y + height/2)));
        ViewFx.gc.setFill(new Color(color.getR(), color.getG(), color.getB(), color.getA()));
        ViewFx.gc.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
        ViewFx.gc.restore();
    }
}

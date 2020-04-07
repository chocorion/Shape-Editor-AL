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
import javafx.stage.Screen;
import javafx.stage.Stage;


public class ViewFx extends Application implements ConcreteViewItf {
    private static GraphicsContext gc;
    private ControllerFx controller;
    private static Model model;

    public ViewFx() {}

    public ViewFx(Model model) {
        model = model;
        System.out.println("Model is " + model);
    }

    public void initViewFx () {
        Application.launch(ViewFx.class, (String[]) null);
    }


    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Shape Editor");
        model = Model.currentModel;


        Group root = new Group();

        Canvas canvas = new Canvas(Model.WIDTH, Model.HEIGHT);
        ViewFx.gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        Scene rootScene = new Scene(root);
        this.controller = new ControllerFx(model, rootScene);
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
        controller.onStart();
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
        System.out.println("Drawing rectangle !" + ViewFx.gc + " " + rectangle);
        ViewFx.gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    @Override
    public void devDrawPolygon(Polygon polygon) {
        ViewFx.gc.fillRect(50, 50, 100, 150);
    }
}

package application.ui.javafx;


import application.controller.Controller;
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
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class ViewFx extends Application implements ConcreteViewItf {
    private static Model model;
    private static Scene rootScene;
    private static GraphicsContext gc;

    public ViewFx() {}

    public ViewFx(Model model) {
        ViewFx.model = model;

        Group root = new Group();

        Canvas canvas = new Canvas(model.getWidth(), model.getHeight());
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
        System.out.println("Drawing rectangle !" + ViewFx.gc + " " + rectangle);

        if (rectangle.getColor() == application.model.shape.Color.BLUE) {
            ViewFx.gc.setFill(Color.BLUE);
        } else if (rectangle.getColor() == application.model.shape.Color.LIGHT_GREY) {
            ViewFx.gc.setFill(Color.gray(0.8));
        } else if (rectangle.getColor() == application.model.shape.Color.DARK_GREY) {
            ViewFx.gc.setFill(Color.gray(0.2));
        } else if (rectangle.getColor() == application.model.shape.Color.WHITE) {
            ViewFx.gc.setFill(Color.WHITE);
        }else {
            ViewFx.gc.setFill(Color.BLACK);
        }

        ViewFx.gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    @Override
    public void devDrawPolygon(Polygon polygon) {
        ViewFx.gc.fillRect(50, 50, 100, 150);
    }

    public void drawTopBar() {
        //TEMPORARY
        ViewFx.gc.setFill(Color.gray(190));
        ViewFx.gc.fillRect(0, 0, this.getWidth(), 10);

        ViewFx.gc.setFill(Color.gray(110));
        ViewFx.gc.fillRect(2, 2, 6, 6);
        ViewFx.gc.fillRect(12, 2, 6, 6);
    }
}

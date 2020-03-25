package application.ui.javafx;


import application.model.shape.Polygon;
import application.model.shape.Rectangle;
import application.view.ConcreteViewItf;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;


public class ViewFx extends Application implements ConcreteViewItf {
    private GraphicsContext gc;

    public ViewFx() {}

    public void initViewFx () {
        Application.launch(ViewFx.class, (String[]) null);
    }


    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Shape Editor");

        Group root = new Group();

        Canvas canvas = new Canvas(640, 480);
        this.gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void devDrawRectangle(Rectangle rectangle) {
        this.gc.fillRect(50, 50, 100, 150);
    }

    @Override
    public void devDrawPolygon(Polygon polygon) {
        this.gc.fillRect(50, 50, 100, 150);
    }
}

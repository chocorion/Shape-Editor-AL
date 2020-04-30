package application.ui.javafx;


import application.controller.Controller;
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

import java.util.ArrayList;
import java.util.List;


public class ViewFx extends Application implements ConcreteViewItf {
    private static Model model;
    private static Scene rootScene;
    private static GraphicsContext gc;

    private int menuX, menuY,viewWhiteBoardW,viewWhiteBoardH;
    private List<String> menuName;


    public ViewFx() {}


    public ViewFx(Model model) {
        ViewFx.model = model;

        Group root = new Group();

        Canvas canvas = new Canvas(model.getWidth(), model.getHeight());
        ViewFx.gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        ViewFx.rootScene = new Scene(root);
        this.menuName = new ArrayList<String>();
        this.menuName.add("group");
        this.menuName.add("ungoup");
        this.menuName.add("color");
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
    public void devDrawSelection(int x, int y, int width, int height){
        System.out.println("devDrawSelection");
        ViewFx.gc.setStroke(Color.LIGHTGRAY);
        ViewFx.gc.strokeRect(x,y,width,height);
    }






    @Override
    public void devUndrawSelect(int x, int y, int width, int height){
        ViewFx.gc.clearRect(x,y,width+1,height+1);
    }


    @Override
    public void devUndrawMenu(){
        ViewFx.gc.clearRect(menuX,menuY,1+viewWhiteBoardW /20, 1+this.menuName.size() * (viewWhiteBoardH/40));

    }

    @Override
    public void devDrawText(String text, int x, int y, int size, application.utils.Color color) {
        ViewFx.gc.setFill(new Color(color.getR(), color.getG(), color.getB(), color.getA()));
        ViewFx.gc.fillText(text, x, y, size);
    }


}

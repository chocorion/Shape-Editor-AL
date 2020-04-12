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
        System.out.println("Drawing rectangle !" + ViewFx.gc + " " + rectangle);

        application.utils.Color rectColor = rectangle.getColor();
        ViewFx.gc.setFill(new Color(rectColor.getR(), rectColor.getG(), rectColor.getB(), rectColor.getA()));


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

    @Override
    public void devDrawSelection(int x, int y, int width, int height){
        System.out.println("devDrawSelection");
        ViewFx.gc.setStroke(Color.LIGHTGRAY);
        ViewFx.gc.strokeRect(x,y,width,height);
    }



    private void drawMenuItem(int width, int height, int index){
        ViewFx.gc.setFill(Color.LIGHTGRAY);
        ViewFx.gc.fillRect(this.menuX,this.menuY+height*index,width,height);
        ViewFx.gc.setStroke(Color.GREY);
        ViewFx.gc.setFill(Color.BLACK);
        ViewFx.gc.strokeRect(this.menuX,this.menuY+(index*height),width,height);
        ViewFx.gc.fillText(this.menuName.get(index),this.menuX+width/10,this.menuY+index*height+(height*2/3), width);
    }
    @Override
    public void devAddPopUpMenu(int x, int y){
        this.menuX =x;
        this.menuY =y;
        System.out.println("menu");
        int coeffW = this.getWidth() / this.model.getWidth();
        int coeffH = this.getHeight() / this.model.getHeight();

        int whiteBoardW = this.model.getWhiteBoard().getWidth();
        int whiteBoardH = this.model.getWhiteBoard().getHeight();

        this.viewWhiteBoardW = whiteBoardW*coeffW;
        this.viewWhiteBoardH = whiteBoardH*coeffH;

        int width =viewWhiteBoardW /20;
        int height = viewWhiteBoardH/40;

        for (int index=0; index< this.menuName.size(); index ++){
            drawMenuItem(width,height,index);
        }

    }
    @Override
    public boolean devClickOnGroup(int x,int y){
        System.out.println("click on group");
        if( x < this.menuX+ this.viewWhiteBoardW/20 && x > this.menuX && y > this.menuY && y< this.menuY+this.viewWhiteBoardH/40)
        return true;
        else return false;
    }


    @Override
    public boolean devClickOnUnGroup(int x,int y){
        System.out.println("click on group");
        if( x < this.menuX+ this.viewWhiteBoardW/20 && x > this.menuX && y > this.menuY && y< this.menuY+2*(this.viewWhiteBoardH/40))
            return true;
        else return false;
    }

    @Override
    public void devUndrawSelect(int x, int y, int width, int height){
        ViewFx.gc.clearRect(x,y,width+1,height+1);
    }


    @Override
    public void devUndrawMenu(){
        ViewFx.gc.clearRect(menuX,menuY,1+viewWhiteBoardW /20, 1+this.menuName.size() * (viewWhiteBoardH/40));

    }




}

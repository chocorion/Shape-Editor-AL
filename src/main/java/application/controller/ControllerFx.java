package application.controller;

import java.util.ArrayList;

import application.model.Model;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

/**
 * This class get all the input from the user, and calls the associate fonction in scene manager.
 */
public class ControllerFx {
    ArrayList<String> input;
    Point2D mousePos;
    private Model model;

    /**
     * Basic constructor. Init all user events.
     * @param scene			Javafx scene
     */
    public ControllerFx(Model model, Scene scene) {
        if (model == null) {
            System.err.println("Error in controllerFx constructor : Model can't be null");
            System.exit(1);
        }
        this.input = new ArrayList<>();
        this.model = model;

        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        System.out.println("Key pressed -> " + code);
                        if (!input.contains(code))
                            input.add(code);
                    }
                });

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        input.remove(code);
                    }
                });

        scene.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                        if(e.getButton() == MouseButton.PRIMARY)
                            mouseClicked(0, e.getX(), e.getY());
                        if(e.getButton() == MouseButton.SECONDARY)
                            mouseClicked(1, e.getX(), e.getY());
                    }
                });

        scene.setOnMousePressed(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                        setMousePos(e.getX(), e.getY());
                        String code;
                        if(e.getButton() == MouseButton.PRIMARY) {
                            code = "MOUSE_LEFT";
                            if ( !input.contains(code) )
                                input.add( code );
                        }
                        if(e.getButton() == MouseButton.SECONDARY){
                            code = "MOUSE_RIGHT";
                            if ( !input.contains(code) )
                                input.add( code );
                        }

                    }
                });

        scene.setOnMouseReleased(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                        if(e.getButton() == MouseButton.PRIMARY)
                            releasedMouseLeft();
                        if(e.getButton() == MouseButton.SECONDARY)
                            input.remove("MOUSE_RIGHT");
                    }
                });

        scene.setOnMouseDragged(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                        setMousePos(e.getX(), e.getY());
                    }
                });

        scene.setOnScroll(
                new EventHandler<ScrollEvent>()
                {
                    public void handle(ScrollEvent e) {
                        moveWheel((int)e.getDeltaY());
                    }
                });
    }

    /**
     * Calls the fonction associated in sceneManager when the mouse if released.
     */
    private void releasedMouseLeft() {
        this.input.remove("MOUSE_LEFT");
        //this.sceneManager.releasedMouseLeft(this.mousePos.getX(), this.mousePos.getY(), this.input);
    }

    /**
     * Set the mouse's position.
     * @param x Position x
     * @param y	Position y
     */
    private void setMousePos(double x, double y) {
        this.mousePos = new Point2D(x, y);
    }

    /**
     * Calls the associated fonction in sceneManager with the mouse's position when the mouse is clicked.
     * @param button Button of the mouse(left : 0, right : 1)
     * @param x Position x
     * @param y	Position y
     */
    private void mouseClicked(int button, double x, double y) {
        //this.sceneManager.mouseClicked(button, x, y, this.input);
    }

    /**
     * Calls the associated fonction in sceneManager with the vertical delta of the wheel when it is moving.
     * @param dy Wheel's vertical delta
     */
    private void moveWheel(int dy) {
        int speed = 5;
        //this.sceneManager.moveWheel(dy / speed);
    }

    /**
     * Main fonction of the class, called every step of game's main loop. It manage all the inputs it receives from
     * the user events.
     */
    public void tick() {
        if(this.input.contains("ESCAPE")){
            this.input.remove("ESCAPE");
            //this.sceneManager.inputEscape();
        }
        if(this.input.contains("MOUSE_LEFT")){
            //this.sceneManager.inputMouseLeft(this.mousePos.getX(), this.mousePos.getY());
        }
        if(this.input.contains("CONTROL")){
            if(this.input.contains("S")) {
                this.input.remove("S");
                //this.sceneManager.saveGame();
            }
            else if(this.input.contains("L")) {
                this.input.remove("L");
                //this.sceneManager.restoreGame();
            }
        }
    }

    public void onStart() {
        System.out.println("CONTROLLER ON START");
        this.model.update();
    }
}

package application.ui.javafx;

import java.util.ArrayList;

import application.Main;
import application.controller.Controller;
import application.controller.MainController;
import application.model.Model;
import application.ui.javafx.ViewFx;
import application.view.View;
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
public class ControllerFx implements Controller {
    ArrayList<String> input;
    Point2D mousePos;

    /**
     * Basic constructor. Init all user events.
     */
    public ControllerFx(MainController mainController, ViewFx view) {
        this.input = new ArrayList<>();

        Scene scene = view.getScene();

        scene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    System.out.println("Key pressed -> " + code);
                    if (!input.contains(code))
                        input.add(code);
                });

        scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove(code);
                });


        scene.setOnMousePressed(
                e -> {
                    if(e.getButton() == MouseButton.PRIMARY) {
                        mainController.onLeftClickPressed(e.getX(), e.getY());
                    }

                    if(e.getButton() == MouseButton.SECONDARY){
                        mainController.onRightClickPressed(e.getX(), e.getY());
                    }
                });

        scene.setOnMouseReleased(
                e -> {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        mainController.onLeftClickReleased(e.getX(), e.getY());
                    }
                    if (e.getButton() == MouseButton.SECONDARY) {
                        mainController.onRightClickReleased(e.getX(), e.getY());
                    }
                });

        scene.setOnMouseDragged(
                e -> mainController.onMouseDragged(e.getX(), e.getY()));

        scene.setOnScroll(
                e -> moveWheel((int)e.getDeltaY()));
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
    public boolean tick() {
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

        return true;
    }

    public void onStart() {
        System.out.println("CONTROLLER ON START");
    }
}

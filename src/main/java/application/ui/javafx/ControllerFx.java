package application.ui.javafx;

import java.util.ArrayList;

import application.controller.ConcreteControllerItf;
import application.controller.MainController;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;

/**
 * This class get all the input from the user, and calls the associate fonction in scene manager.
 */
public class ControllerFx implements ConcreteControllerItf {
    ArrayList<String> input;
    ArrayList<String> specialKeys;

    /**
     * Basic constructor. Init all user events.
     */
    public ControllerFx(MainController mainController, ViewFx view) {
        this.input = new ArrayList<>();
        specialKeys = new ArrayList<>();
        specialKeys.add("ENTER");
        specialKeys.add("CONTROL");
        specialKeys.add("BACKSPACE");

        Scene scene = view.getScene();

        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            mainController.onWindowsResize(newVal.intValue(), (int) scene.getHeight());
        });

        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            mainController.onWindowsResize((int) scene.getWidth(), newVal.intValue());
        });

        scene.setOnKeyPressed(
                e -> {
                    if (specialKeys.contains(e.getCode().toString()))
                        mainController.onKeyPressed(e.getCode().toString());
                    else
                        mainController.onKeyPressed(e.getText());
                });

        scene.setOnKeyReleased(
                e -> {
                    if (specialKeys.contains(e.getCode().toString()))
                        mainController.onKeyReleased(e.getCode().toString());
                    else
                        mainController.onKeyReleased(e.getText());
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

        scene.setOnMouseMoved(
                e -> mainController.onMouseMoved(e.getX(), e.getY())
        );

        scene.setOnMouseDragged(
                e -> mainController.onMouseMoved(e.getX(), e.getY())
        );
    }


    public boolean tick() {

        return true;
    }

}

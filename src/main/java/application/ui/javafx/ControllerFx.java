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

    /**
     * Basic constructor. Init all user events.
     */
    public ControllerFx(MainController mainController, ViewFx view) {
        this.input = new ArrayList<>();

        Scene scene = view.getScene();

        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            mainController.onWindowsResize(newVal.intValue(), (int) scene.getHeight());
        });

        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            mainController.onWindowsResize((int) scene.getWidth(), newVal.intValue());
        });

        scene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    mainController.onKeyPressed(code);
                });

        scene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    mainController.onKeyReleased(code);
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

package application.model;

import application.model.areas.ToolBar;
import application.model.areas.WhiteBoard;
import application.model.command.CommandManager;

public class Model {
    private CommandManager commands;

    public int width, height;

    public static Model currentModel;

    private ToolBar toolBar;
    private WhiteBoard whiteBoard;

    public Model() {
        System.out.println("Model create");

        this.commands = new CommandManager();

        this.toolBar    = new ToolBar(this);
        this.whiteBoard = new WhiteBoard(this);

        currentModel = this;

        width = 640;
        height = 480;
    }

    public void update() {
        this.toolBar.update();
        this.whiteBoard.update();
    }

    public ToolBar getToolBar() {
        return toolBar;
    }

    public WhiteBoard getWhiteBoard() {
        return whiteBoard;
    }
    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

}

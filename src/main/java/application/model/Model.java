package application.model;

import application.model.areas.ToolBar;
import application.model.areas.WhiteBoard;
import application.model.command.CommandManager;

public class Model {
    private CommandManager commands;

    private ToolBar toolBar;
    private WhiteBoard whiteBoard;

    public Model() {
        System.out.println("Model create");

        this.commands = new CommandManager();

        this.toolBar = new ToolBar();
        this.whiteBoard = new WhiteBoard();
    }
}

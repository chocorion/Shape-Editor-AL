package application.model;

import application.model.areas.ToolBar;
import application.model.areas.WhiteBoard;
import application.model.command.Command;
import application.model.command.CommandManager;

public class Model {
    private CommandManager commands;

    public static Model currentModel;

    private ToolBar toolBar;
    private WhiteBoard whiteBoard;


    public Model() {
        commands = new CommandManager();

        // Needed by javaFx implementation..
        currentModel = this;

        toolBar    = new ToolBar(this);
        whiteBoard = new WhiteBoard(this);
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


    public void execute(Command command) {
        commands.execute(command);
    }

    public void undo() {
        commands.undo();
    }

    public void redo() {
        commands.redo();
    }

}

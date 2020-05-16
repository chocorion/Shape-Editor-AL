package application.model;

import application.model.Memento.ToolBarState;
import application.model.areas.ToolBar;
import application.model.areas.WhiteBoard;
import application.model.command.Command;
import application.model.command.CommandManager;

public class Model {
    private CommandManager commands;

    public static Model currentModel;

    private ToolBar toolBar;
    private WhiteBoard whiteBoard;
    private ToolBarState toolBarState;


    public Model() {
        commands = new CommandManager();

        // Needed by javaFx implementation..
        currentModel = this;
        this.toolBarState = new ToolBarState("");
        toolBar    = new ToolBar(this);
        toolBar.restore(this.toolBarState);
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

    public void hitSave() {
        this.toolBarState = this.toolBar.save();
    }
}

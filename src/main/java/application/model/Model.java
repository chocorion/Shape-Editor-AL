package application.model;

import application.model.Memento.ToolBarState;
import application.model.areas.ToolBar;
import application.model.areas.WhiteBoard;
import application.model.command.Command;
import application.model.command.CommandManager;

/**
 * Represent the main model of the application.
 */
public class Model {
    private final CommandManager commands;

    public static Model currentModel;

    private final ToolBar toolBar;
    private final WhiteBoard whiteBoard;
    private ToolBarState toolBarState;


    /**
     * Default constructor.
     */
    public Model() {
        commands = new CommandManager();

        // Needed by javaFx implementation..
        currentModel = this;
        this.toolBarState = new ToolBarState("");
        toolBar = new ToolBar(this);
        toolBar.restore(this.toolBarState);
        whiteBoard = new WhiteBoard(this);
    }


    /**
     * Update all the model elements.
     */
    public void update() {
        this.toolBar.update();
        this.whiteBoard.update();
    }


    /**
     * Return the current toolbar.
     * @return Current toolbar.
     */
    public ToolBar getToolBar() {
        return toolBar;
    }


    /**
     * Return the current whiteboard.
     * @return Current whiteboard.
     */
    public WhiteBoard getWhiteBoard() {
        return whiteBoard;
    }


    /**
     * Execute the given command/
     * @param command Command to execute.
     */
    public void execute(Command command) {
        commands.execute(command);
    }


    /**
     * Undo the last command if exists.
     */
    public void undo() {
        commands.undo();
    }


    /**
     * Redo the last undo command if no action where made since.
     */
    public void redo() {
        commands.redo();
    }


    /**
     * Save the state of the toolbar.
     */
    public void hitSave() {
        this.toolBarState = this.toolBar.save();
    }
}

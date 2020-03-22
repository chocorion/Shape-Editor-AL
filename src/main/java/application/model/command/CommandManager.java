package application.model.command;

import java.util.Queue;
import java.util.Stack;

public class CommandManager {

    private Stack<Command> commandAdded;
    private Stack<Command> commandRemoved;

    private boolean isRemovedClean;

    public CommandManager() {
        this.commandAdded   = new Stack<>();
        this.commandRemoved = new Stack<>();

        this.isRemovedClean = true;
    }

    public void execute(Command cmd) {
        cmd.execute();

        this.push(cmd);
    }

    private void push(Command cmd) {
        this.commandAdded.push(cmd);

        this.isRemovedClean = false;
    }

    private Command pop() {
        return this.commandAdded.pop();
    }

    public void undo() {
        // TODO
    }

    public void redo() {
        // TODO
    }
}

package application.model.command;

import java.util.Stack;

public class CommandManager {

    private final Stack<Command> commandAdded;
    private final Stack<Command> commandRemoved;

    private boolean isRemovedClean;

    public CommandManager() {
        commandAdded   = new Stack<>();
        commandRemoved = new Stack<>();

        isRemovedClean = true;
    }

    public void execute(Command cmd) {
        cmd.execute();

        commandAdded.push(cmd);
        isRemovedClean = false;

        while (!commandRemoved.empty()) {
            commandRemoved.pop();
        }
    }

    public void undo() {
        if (commandAdded.empty()) {
            return;
        }

        Command cmd = commandAdded.pop();
        cmd.inverse();

        commandRemoved.push(cmd);
        isRemovedClean = true;
    }

    public void redo() {
        if (isRemovedClean && !commandRemoved.empty()) {
            Command cmd = commandRemoved.pop();
            cmd.execute();

            commandAdded.push(cmd);
        }
    }
}

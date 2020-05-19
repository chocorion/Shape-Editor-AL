package application.model.command;

import java.util.Stack;

/**
 * Represent the command's caretaker.
 */
public class CommandManager {

    private final Stack<Command> commandAdded;
    private final Stack<Command> commandRemoved;

    private boolean isRemovedClean;


    /**
     * Default constructor.
     */
    public CommandManager() {
        commandAdded   = new Stack<>();
        commandRemoved = new Stack<>();

        isRemovedClean = true;
    }


    /**
     * Execute the given command and store it to possibly inverse it later.
     * @param cmd The command to execute.
     */
    public void execute(Command cmd) {
        cmd.execute();

        commandAdded.push(cmd);
        isRemovedClean = false;

        while (!commandRemoved.empty()) {
            commandRemoved.pop();
        }
    }


    /**
     * Undo the last command.
     */
    public void undo() {
        if (commandAdded.empty()) {
            return;
        }

        Command cmd = commandAdded.pop();
        cmd.inverse();

        commandRemoved.push(cmd);
        isRemovedClean = true;
    }


    /**
     * Redo the last command undo, if no command was execute since the undo.
     */
    public void redo() {
        if (isRemovedClean && !commandRemoved.empty()) {
            Command cmd = commandRemoved.pop();
            cmd.execute();

            commandAdded.push(cmd);
        }
    }
}

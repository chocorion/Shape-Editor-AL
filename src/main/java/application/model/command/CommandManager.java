package application.model.command;

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

        this.commandAdded.push(cmd);
        this.isRemovedClean = false;

        while (!this.commandRemoved.empty()) {
            this.commandRemoved.pop();
        }
    }

    public void undo() {
        System.out.println("In undo :");
        if (this.commandAdded.empty()) {
            System.out.println("\tcommandAdded empty...");
            return;
        }

        Command cmd = this.commandAdded.pop();
        System.out.println("\t command poped -> " + cmd);
        cmd.inverse();

        this.commandRemoved.push(cmd);
        this.isRemovedClean = true;
    }

    public void redo() {
        if (this.isRemovedClean && !this.commandRemoved.empty()) {
            Command cmd = this.commandRemoved.pop();
            cmd.execute();

            this.commandAdded.push(cmd);
        }
    }
}

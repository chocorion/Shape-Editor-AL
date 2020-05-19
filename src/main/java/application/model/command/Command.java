package application.model.command;

/**
 * Represent a command that can be executed by the model.
 * This command can be reversed.
 */
public interface Command {
    /**
     * Execute the command.
     */
    void execute();


    /**
     * Reverse the effect of the command.
     */
    void inverse();
}

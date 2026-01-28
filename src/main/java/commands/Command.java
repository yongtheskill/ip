package commands;

import tasks.TaskList;
import ui.Ui;
import exceptions.SixException;

/**
 * Represents an executable command in the chatbot.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list and UI.
     *
     * @param tasks The task list to operate on.
     * @param ui The UI to interact with the user.
     * @throws SixException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui) throws SixException;

    public boolean isBye() {
        return false;
    }
}

package commands;

import tasks.TaskList;
import ui.Ui;
import exceptions.SixException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui) throws SixException;

    public boolean isBye() {
        return false;
    }
}

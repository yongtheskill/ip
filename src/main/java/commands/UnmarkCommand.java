package commands;

import tasks.*;
import ui.Ui;
import exceptions.InvalidParameterException;

public class UnmarkCommand extends Command {
    private final int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidParameterException {
        Task task = tasks.getTask(idx);
        task.markAsUndone();
        tasks.save();
        String response = "OK, I've marked this task as not done yet:\n " + task;
        ui.showMessage(response);
    }

}


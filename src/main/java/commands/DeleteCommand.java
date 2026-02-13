package commands;

import tasks.Task;
import tasks.TaskList;
import ui.Ui;
import exceptions.InvalidParameterException;

public class DeleteCommand extends Command {
    private final int idx;

    public DeleteCommand(int idx) {
        this.idx = idx - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidParameterException {
        Task task = tasks.getTask(idx);
        tasks.deleteTask(idx);
        String response = "Noted. I've removed this task:\n " + task + "\nNow you have " + tasks.getSize()
                + " tasks(s) in the list.";
        ui.showMessage(response);
    }

}

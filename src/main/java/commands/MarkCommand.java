package commands;

import tasks.*;
import exceptions.InvalidParameterException;

public class MarkCommand extends Command {
    private final int idx;

    public MarkCommand(int idx) {
        this.idx = idx - 1;
    }

    @Override
    public void execute(TaskList tasks) throws InvalidParameterException {
        Task task = tasks.getTask(idx);
        task.markAsDone();
        tasks.save();
        String response = "Nice! I've marked this task as done:\n " + task;
        Command.printMessage(response);
    }

}

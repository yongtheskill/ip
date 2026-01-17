package commands;

import tasks.*;

public class UnmarkCommand extends Command {
    private final int idx;

    public UnmarkCommand(String idx) {
        this.idx = Integer.parseInt(idx) - 1;
    }

    @Override
    public void execute(TaskList tasks) {
        Task task = tasks.getTask(idx);
        task.markAsUndone();
        String response = "OK, I've marked this task as not done yet:\n " + task;
        Command.printMessage(response);
    }

}

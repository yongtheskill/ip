package commands;

import tasks.*;

public class MarkCommand extends Command {
    private final int idx;

    public MarkCommand(String idx) {
        this.idx = Integer.parseInt(idx) - 1;
    }

    @Override
    public void execute(TaskList tasks) {
        Task task = tasks.getTask(idx);
        task.markAsDone();
        String response = "Nice! I've marked this task as done:\n " + task;
        Command.printMessage(response);
    }

}

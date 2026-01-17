package commands;

import tasks.*;
import exceptions.InvalidParameterException;

public class DeleteCommand extends Command {
    private final int idx;

    public DeleteCommand(int idx) {
        this.idx = idx - 1;
    }

    @Override
    public void execute(TaskList tasks) throws InvalidParameterException {
        Task task = tasks.getTask(idx);
        tasks.deleteTask(idx);
        String response = "Noted. I've removed this task:\n " + task + "\nNow you have " + tasks.getSize()
                + " tasks(s) in the list.";
        Command.printMessage(response);
    }

}

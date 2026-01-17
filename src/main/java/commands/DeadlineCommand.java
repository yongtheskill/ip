package commands;

import tasks.*;

public class DeadlineCommand extends AddCommand {
    private final String item;
    private final String by;

    public DeadlineCommand(String item, String by) {
        this.item = item;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks) {
        Task newTask = new Deadline(this.item, this.by);
        tasks.addTask(newTask);
        AddCommand.getAddMessage(tasks, newTask);
    }

}

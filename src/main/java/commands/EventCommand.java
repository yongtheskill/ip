package commands;

import tasks.*;

public class EventCommand extends AddCommand {
    private final String item;
    private final String from;
    private final String to;

    public EventCommand(String item, String from, String to) {
        this.item = item;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks) {
        Task newTask = new Event(this.item, this.from, this.to);
        tasks.addTask(newTask);
        AddCommand.getAddMessage(tasks, newTask);
    }

}

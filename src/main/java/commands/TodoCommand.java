package commands;

import tasks.*;

public class TodoCommand extends AddCommand {
    private final String item;

    public TodoCommand(String item) {
        this.item = item;
    }

    @Override
    public void execute(TaskList tasks) {
        Task newTask = new Todo(this.item);
        tasks.addTask(newTask);
        AddCommand.getAddMessage(tasks, newTask);
    }

}

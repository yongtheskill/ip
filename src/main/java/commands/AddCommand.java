package commands;

import tasks.*;

public class AddCommand extends Command {
    private final String item;

    public AddCommand(String item) {
        this.item = item;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.addTask(new Task(this.item));
        Command.printMessage("added: " + this.item);
    }

}

package commands;

import tasks.TaskList;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks) {
        Command.printMessage("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isBye() {
        return true;
    }
}

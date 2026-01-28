package commands;

import tasks.TaskList;
import ui.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isBye() {
        return true;
    }
}


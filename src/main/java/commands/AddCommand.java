package commands;

import tasks.*;
import ui.Ui;

public abstract class AddCommand extends Command {
    public static void getAddMessage(TaskList tasks, Task task, Ui ui) {
        String response = "Got it. I've added this task:\n " + task + "\nNow you have " + tasks.getSize()
                + " tasks in the list.";
        ui.showMessage(response);
    }
}

package commands;

import tasks.*;

public abstract class AddCommand extends Command {
    public static void getAddMessage(TaskList tasks, Task task) {
        String response = "Got it. I've added this task:\n " + task + "\nNow you have " + tasks.getSize()
                + " tasks in the list.";
        Command.printMessage(response);
    }
}

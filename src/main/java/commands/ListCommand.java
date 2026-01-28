package commands;

import tasks.*;
import ui.Ui;
import exceptions.InvalidParameterException;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidParameterException {
        if (tasks.getSize() == 0) {
            ui.showMessage("Your task list is empty.");
            return;
        }

        StringBuilder response = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            response.append((i + 1)).append(". ").append(tasks.getTask(i).toString()).append("\n");
        }
        ui.showMessage(response.toString().trim());
    }
}


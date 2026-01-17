package commands;

import tasks.*;
import exceptions.InvalidParameterException;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks) throws InvalidParameterException {
        if (tasks.getSize() == 0) {
            Command.printMessage("Your task list is empty.");
            return;
        }

        StringBuilder response = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            response.append((i + 1)).append(". ").append(tasks.getTask(i).toString()).append("\n");
        }
        Command.printMessage(response.toString().trim());
    }
}

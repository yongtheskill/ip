package commands;

import tasks.*;
import ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task[] matchingTasks = tasks.findTasks(keyword);

        if (matchingTasks.length == 0) {
            ui.showMessage("No matching tasks found.");
            return;
        }

        StringBuilder response = new StringBuilder();
        response.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.length; i++) {
            response.append((i + 1)).append(".").append(matchingTasks[i].toString()).append("\n");
        }
        ui.showMessage(response.toString().trim());
    }
}

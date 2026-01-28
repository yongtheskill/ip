import commands.Command;
import tasks.TaskList;
import storage.Storage;
import ui.Ui;
import exceptions.SixException;

/**
 * Main class for the Six chatbot application.
 */
public class Six {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();

        boolean exit = false;
        TaskList tasks = Storage.load();

        while (!exit) {
            try {
                String cmd = ui.readCommand();
                Command command = Parser.parse(cmd);
                command.execute(tasks, ui);
                exit = command.isBye();
            } catch (SixException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }
}


import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UnmarkCommand;
import exceptions.InvalidParameterException;
import exceptions.SixException;
import exceptions.UnknownCommandException;

/**
 * Parses user input commands and converts them into executable Command objects.
 */
public class Parser {
    /**
     * Parses a command string and returns the corresponding Command object.
     *
     * @param cmd The command string to parse.
     * @return The Command object corresponding to the input.
     * @throws SixException If the command is invalid or unknown.
     */
    public static Command parse(String cmd) throws SixException {
        if (cmd == null || cmd.trim().isEmpty()) {
            throw new InvalidParameterException("Command cannot be empty!");
        }

        cmd = cmd.trim();

        if (cmd.equals("bye")) {
            return new ByeCommand();
        }

        if (cmd.equals("list")) {
            return new ListCommand();
        }

        if (cmd.equals("mark") || cmd.startsWith("mark ")) {
            return parseMarkCommand(cmd);
        }

        if (cmd.equals("unmark") || cmd.startsWith("unmark ")) {
            return parseUnmarkCommand(cmd);
        }

        if (cmd.equals("todo") || cmd.startsWith("todo ")) {
            return parseTodoCommand(cmd);
        }

        if (cmd.equals("deadline") || cmd.startsWith("deadline ")) {
            return parseDeadlineCommand(cmd);
        }

        if (cmd.equals("event") || cmd.startsWith("event ")) {
            return parseEventCommand(cmd);
        }

        if (cmd.equals("delete") || cmd.startsWith("delete ")) {
            return parseDeleteCommand(cmd);
        }

        if (cmd.equals("find") || cmd.startsWith("find ")) {
            return parseFindCommand(cmd);
        }

        if (cmd.equals("remind") || cmd.startsWith("remind ")) {
            return parseRemindCommand(cmd);
        }

        throw new UnknownCommandException("I don't understand that command! Please try again.");
    }

    private static Command parseMarkCommand(String cmd) throws InvalidParameterException {
        if (cmd.equals("mark")) {
            throw new InvalidParameterException("Please specify a task number to mark!");
        }
        String indexStr = cmd.substring(5).trim();
        if (indexStr.isEmpty()) {
            throw new InvalidParameterException("Please specify a task number to mark!");
        }
        try {
            int index = Integer.parseInt(indexStr);
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidParameterException("Task number must be a valid integer!");
        }
    }

    private static Command parseUnmarkCommand(String cmd) throws InvalidParameterException {
        if (cmd.equals("unmark")) {
            throw new InvalidParameterException("Please specify a task number to unmark!");
        }
        String indexStr = cmd.substring(7).trim();
        if (indexStr.isEmpty()) {
            throw new InvalidParameterException("Please specify a task number to unmark!");
        }
        try {
            int index = Integer.parseInt(indexStr);
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidParameterException("Task number must be a valid integer!");
        }
    }

    private static Command parseTodoCommand(String cmd) throws InvalidParameterException {
        if (cmd.equals("todo")) {
            throw new InvalidParameterException("The description of a todo cannot be empty!");
        }
        String description = cmd.substring(5).trim();
        if (description.isEmpty()) {
            throw new InvalidParameterException("The description of a todo cannot be empty!");
        }
        return new TodoCommand(description);
    }

    private static Command parseDeadlineCommand(String cmd) throws InvalidParameterException {
        if (cmd.equals("deadline")) {
            throw new InvalidParameterException("The description of a deadline cannot be empty!");
        }
        String details = cmd.substring(9).trim();
        if (details.isEmpty()) {
            throw new InvalidParameterException("The description of a deadline cannot be empty!");
        }
        String[] parts = details.split(" /by ");
        if (parts.length < 2) {
            throw new InvalidParameterException("Deadline must have a /by parameter!");
        }
        if (parts[0].trim().isEmpty()) {
            throw new InvalidParameterException("The description of a deadline cannot be empty!");
        }
        if (parts[1].trim().isEmpty()) {
            throw new InvalidParameterException("The deadline time cannot be empty!");
        }
        return new DeadlineCommand(parts[0].trim(), parts[1].trim());
    }

    private static Command parseEventCommand(String cmd) throws InvalidParameterException {
        if (cmd.equals("event")) {
            throw new InvalidParameterException("The description of an event cannot be empty!");
        }
        String details = cmd.substring(6).trim();
        if (details.isEmpty()) {
            throw new InvalidParameterException("The description of an event cannot be empty!");
        }
        String[] parts = details.split(" /from | /to ");
        if (parts.length < 3) {
            throw new InvalidParameterException("Event must have /from and /to parameters!");
        }
        if (parts[0].trim().isEmpty()) {
            throw new InvalidParameterException("The description of an event cannot be empty!");
        }
        if (parts[1].trim().isEmpty()) {
            throw new InvalidParameterException("The event start time cannot be empty!");
        }
        if (parts[2].trim().isEmpty()) {
            throw new InvalidParameterException("The event end time cannot be empty!");
        }
        return new EventCommand(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    private static Command parseDeleteCommand(String cmd) throws InvalidParameterException {
        if (cmd.equals("delete")) {
            throw new InvalidParameterException("Please specify a task number to delete!");
        }
        String indexStr = cmd.substring(7).trim();
        if (indexStr.isEmpty()) {
            throw new InvalidParameterException("Please specify a task number to delete!");
        }
        try {
            int index = Integer.parseInt(indexStr);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidParameterException("Task number must be a valid integer!");
        }
    }

    private static Command parseFindCommand(String cmd) throws InvalidParameterException {
        if (cmd.equals("find")) {
            throw new InvalidParameterException("Please specify a keyword to search for!");
        }
        String keyword = cmd.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new InvalidParameterException("Please specify a keyword to search for!");
        }
        return new FindCommand(keyword);
    }

    private static Command parseRemindCommand(String cmd) throws InvalidParameterException {
        if (cmd.equals("remind")) {
            return new RemindCommand();
        }

        String daysText = cmd.substring(7).trim();
        if (daysText.isEmpty()) {
            throw new InvalidParameterException("Please specify the number of days as a positive integer!");
        }

        try {
            int days = Integer.parseInt(daysText);
            if (days <= 0) {
                throw new InvalidParameterException("Please specify the number of days as a positive integer!");
            }
            return new RemindCommand(days);
        } catch (NumberFormatException e) {
            throw new InvalidParameterException("Please specify the number of days as a positive integer!");
        }
    }
}

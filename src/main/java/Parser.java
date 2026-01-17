import commands.*;

public class Parser {
    public static Command parse(String cmd) {
        if (cmd.equals("bye"))
            return new ByeCommand();

        if (cmd.equals("list"))
            return new ListCommand();

        if (cmd.startsWith("mark "))
            return new MarkCommand(Integer.parseInt(cmd.substring(5).trim()));

        if (cmd.startsWith("unmark "))
            return new UnmarkCommand(Integer.parseInt(cmd.substring(7).trim()));

        if (cmd.startsWith("todo "))
            return new TodoCommand(cmd.substring(5).trim());

        if (cmd.startsWith("deadline ")) {
            String[] parts = cmd.substring(9).split(" /by ");
            return new DeadlineCommand(parts[0].trim(), parts[1].trim());
        }

        if (cmd.startsWith("event ")) {
            String[] parts = cmd.substring(6).split(" /from | /to ");
            return new EventCommand(parts[0].trim(), parts[1].trim(), parts[2].trim());
        }

        return new TodoCommand(cmd);
    }
}

import commands.*;

public class Parser {
    public static Command parse(String cmd) {
        if (cmd.equals("bye"))
            return new ByeCommand();

        if (cmd.equals("list"))
            return new ListCommand();

        if (cmd.startsWith("mark "))
            return new MarkCommand(cmd.substring(5).trim());

        if (cmd.startsWith("unmark "))
            return new UnmarkCommand(cmd.substring(7).trim());

        return new AddCommand(cmd);
    }
}

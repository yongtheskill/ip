import commands.*;

public class Parser {
    public static Command parse(String cmd) {
        if (cmd.equals("bye")) {
            return new ByeCommand();
        }
        if (cmd.equals("list")) {
            return new ListCommand();
        }
        return new AddCommand(cmd);
    }
}

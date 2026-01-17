import commands.*;

public class Parser {
    public static Command parse(String cmd) {
        if (cmd.equals("bye")) {
            return new ByeCommand();
        }
        return new EchoCommand(cmd);
    }
}

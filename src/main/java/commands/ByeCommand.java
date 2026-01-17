package commands;

public class ByeCommand extends Command {
    @Override
    public void execute() {
        String response = "    ____________________________________________________________\n" + //
                "     Bye. Hope to see you again soon!\n" + //
                "    ____________________________________________________________\n";
        System.out.println(response);
    }

    @Override
    public boolean isBye() {
        return true;
    }
}

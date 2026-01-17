package commands;

public class EchoCommand extends Command {
    private final String message;

    public EchoCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute() {
        String response = "    ____________________________________________________________\n" + //
                "     " + message + "\n" + //
                "    ____________________________________________________________\n";
        System.out.println(response);
    }

}

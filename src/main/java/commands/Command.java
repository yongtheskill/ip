package commands;

import tasks.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks);

    public boolean isBye() {
        return false;
    }

    public static void printMessage(String msg) {
        StringBuilder formatted = new StringBuilder();
        formatted.append("    ____________________________________________________________\n");

        String[] lines = msg.split("\n");
        for (String line : lines) {
            formatted.append("     ").append(line).append("\n");
        }

        formatted.append("    ____________________________________________________________\n");

        System.out.println(formatted.toString());
    }

}

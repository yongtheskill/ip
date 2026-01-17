import java.util.Scanner;

import commands.Command;
import tasks.TaskList;

public class Six {
    public static void main(String[] args) {
        String intro = "    ____________________________________________________________\n" +
                "     Hello! I'm Six\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        System.out.println(intro);

        boolean exit = false;
        TaskList tasks = new TaskList();

        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            String cmd = scanner.nextLine();
            Command command = Parser.parse(cmd);
            command.execute(tasks);
            exit = command.isBye();
        }
        scanner.close();
    }
}

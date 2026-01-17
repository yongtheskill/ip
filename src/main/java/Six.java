import java.util.Scanner;

import commands.Command;
import tasks.TaskList;
import exceptions.SixException;

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
            try {
                String cmd = scanner.nextLine();
                Command command = Parser.parse(cmd);
                command.execute(tasks);
                exit = command.isBye();
            } catch (SixException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     OOPS!!! " + e.getMessage());
                System.out.println("    ____________________________________________________________\n");
            }
        }
        scanner.close();
    }
}

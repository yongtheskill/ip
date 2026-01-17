import java.util.Scanner;

import commands.Command;

public class Six {
    public static void main(String[] args) {
        String intro = "    ____________________________________________________________\n" +
                "     Hello! I'm Six\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        System.out.println(intro);

        boolean exit = false;

        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            String cmd = scanner.nextLine();
            Command command = Parser.parse(cmd);
            command.execute();
            exit = command.isBye();
        }
        scanner.close();
    }
}

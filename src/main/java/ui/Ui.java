package ui;

import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "    ____________________________________________________________";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(DIVIDER);
        System.out.println("     Hello! I'm Six");
        System.out.println("     What can I do for you?");
        System.out.println(DIVIDER);
        System.out.println();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        StringBuilder formatted = new StringBuilder();
        formatted.append(DIVIDER).append("\n");

        String[] lines = message.split("\n");
        for (String line : lines) {
            formatted.append("     ").append(line).append("\n");
        }

        formatted.append(DIVIDER).append("\n");

        System.out.println(formatted.toString());
    }

    public void showError(String message) {
        System.out.println(DIVIDER);
        System.out.println("     OOPS!!! " + message);
        System.out.println(DIVIDER);
        System.out.println();
    }

    public void close() {
        scanner.close();
    }
}

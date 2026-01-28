package commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import tasks.*;
import exceptions.InvalidParameterException;

public class DeadlineCommand extends AddCommand {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final String item;
    private final String by;

    public DeadlineCommand(String item, String by) {
        this.item = item;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks) throws InvalidParameterException {
        LocalDateTime byDateTime;
        try {
            byDateTime = LocalDateTime.parse(this.by, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidParameterException("Invalid date format. Please use yyyy-MM-dd HHmm (e.g., 2019-12-02 1800)");
        }
        Task newTask = new Deadline(this.item, byDateTime);
        tasks.addTask(newTask);
        AddCommand.getAddMessage(tasks, newTask);
    }
}

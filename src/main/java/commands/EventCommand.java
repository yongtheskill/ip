package commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import tasks.*;
import ui.Ui;
import exceptions.InvalidParameterException;

public class EventCommand extends AddCommand {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final String item;
    private final String from;
    private final String to;

    public EventCommand(String item, String from, String to) {
        this.item = item;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidParameterException {
        LocalDateTime fromDateTime;
        LocalDateTime toDateTime;
        try {
            fromDateTime = LocalDateTime.parse(this.from, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidParameterException(
                    "Invalid 'from' date format. Please use yyyy-MM-dd HHmm (e.g., 2019-12-02 1800)");
        }
        try {
            toDateTime = LocalDateTime.parse(this.to, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidParameterException(
                    "Invalid 'to' date format. Please use yyyy-MM-dd HHmm (e.g., 2019-12-02 1800)");
        }
        Task newTask = new Event(this.item, fromDateTime, toDateTime);
        tasks.addTask(newTask);
        AddCommand.getAddMessage(tasks, newTask, ui);
    }
}

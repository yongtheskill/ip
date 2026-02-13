package commands;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;
import exceptions.SixException;

/**
 * Command to show reminders about upcoming tasks.
 * Displays deadlines and events that are due within a specified number of days.
 */
public class RemindCommand extends Command {
    private int days;

    /**
     * Creates a new RemindCommand with the default reminder period of 7 days.
     */
    public RemindCommand() {
        this.days = 7;
    }

    /**
     * Creates a new RemindCommand with a specified reminder period.
     *
     * @param days The number of days to look ahead for reminders.
     */
    public RemindCommand(int days) {
        this.days = days;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws SixException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threshold = now.plusDays(days);

        List<Task> upcomingDeadlines = new ArrayList<>();
        List<Task> upcomingEvents = new ArrayList<>();

        for (Task task : tasks.getTasks()) {
            if (task.isDone()) {
                continue; // Skip completed tasks
            }

            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                LocalDateTime by = deadline.getBy();
                if (by != null && !by.isBefore(now) && !by.isAfter(threshold)) {
                    upcomingDeadlines.add(task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                LocalDateTime from = event.getFrom();
                if (from != null && !from.isBefore(now) && !from.isAfter(threshold)) {
                    upcomingEvents.add(task);
                }
            }
        }

        // Sort by date/time
        upcomingDeadlines.sort(Comparator.comparing(t -> ((Deadline) t).getBy()));
        upcomingEvents.sort(Comparator.comparing(t -> ((Event) t).getFrom()));

        StringBuilder message = new StringBuilder();
        message.append("📅 Reminders for the next ").append(days).append(" day(s):\n");

        if (upcomingDeadlines.isEmpty() && upcomingEvents.isEmpty()) {
            message.append("   No upcoming deadlines or events!");
        } else {
            if (!upcomingDeadlines.isEmpty()) {
                message.append("\n   🔔 Upcoming Deadlines:\n");
                for (Task task : upcomingDeadlines) {
                    Deadline deadline = (Deadline) task;
                    long daysUntil = ChronoUnit.DAYS.between(now, deadline.getBy());
                    long hoursUntil = ChronoUnit.HOURS.between(now, deadline.getBy()) % 24;
                    String timeLeft = formatTimeLeft(daysUntil, hoursUntil);
                    message.append("     • ").append(task.toString()).append("\n");
                    message.append("       ⏰ Due in: ").append(timeLeft).append("\n");
                }
            }

            if (!upcomingEvents.isEmpty()) {
                message.append("\n   📆 Upcoming Events:\n");
                for (Task task : upcomingEvents) {
                    Event event = (Event) task;
                    long daysUntil = ChronoUnit.DAYS.between(now, event.getFrom());
                    long hoursUntil = ChronoUnit.HOURS.between(now, event.getFrom()) % 24;
                    String timeLeft = formatTimeLeft(daysUntil, hoursUntil);
                    message.append("     • ").append(task.toString()).append("\n");
                    message.append("       ⏰ Starts in: ").append(timeLeft).append("\n");
                }
            }
        }

        ui.showMessage(message.toString());
    }

    /**
     * Formats the time left into a human-readable string.
     *
     * @param days  The number of days left.
     * @param hours The number of hours left.
     * @return A formatted string representing the time left.
     */
    private String formatTimeLeft(long days, long hours) {
        if (days == 0 && hours == 0) {
            return "Less than an hour";
        } else if (days == 0) {
            return hours + " hour" + (hours != 1 ? "s" : "");
        } else if (hours == 0) {
            return days + " day" + (days != 1 ? "s" : "");
        } else {
            return days + " day" + (days != 1 ? "s" : "") + " and " + hours + " hour" + (hours != 1 ? "s" : "");
        }
    }
}

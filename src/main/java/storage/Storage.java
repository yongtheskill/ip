package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import tasks.Task;
import tasks.TaskList;
import tasks.Todo;
import tasks.Deadline;
import tasks.Event;

public class Storage {
    private static final String FILE_PATH = "./data/six.txt";
    private static final String DELIMITER = " | ";
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static TaskList load() {
        TaskList tasks = new TaskList();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.addTaskWithoutSaving(task);
                }
            }
        } catch (IOException e) {
            System.out.println("    Warning: Could not load tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    public static void save(TaskList tasks) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();

            FileWriter writer = new FileWriter(file);
            for (Task task : tasks.getTasks()) {
                writer.write(formatTask(task) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("    Warning: Could not save tasks to file: " + e.getMessage());
        }
    }

    private static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String title = parts[2];

        Task task = null;
        switch (type) {
            case "T":
                task = new Todo(title);
                break;
            case "D":
                if (parts.length >= 4) {
                    LocalDateTime by = LocalDateTime.parse(parts[3], STORAGE_FORMAT);
                    task = new Deadline(title, by);
                }
                break;
            case "E":
                if (parts.length >= 5) {
                    LocalDateTime from = LocalDateTime.parse(parts[3], STORAGE_FORMAT);
                    LocalDateTime to = LocalDateTime.parse(parts[4], STORAGE_FORMAT);
                    task = new Event(title, from, to);
                }
                break;
            default:
                return null;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }

    private static String formatTask(Task task) {
        String doneStatus = task.isDone() ? "1" : "0";

        if (task instanceof Todo) {
            return "T" + DELIMITER + doneStatus + DELIMITER + task.getTitle();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D" + DELIMITER + doneStatus + DELIMITER + task.getTitle() + DELIMITER
                    + deadline.getBy().format(STORAGE_FORMAT);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E" + DELIMITER + doneStatus + DELIMITER + task.getTitle() + DELIMITER
                    + event.getFrom().format(STORAGE_FORMAT) + DELIMITER + event.getTo().format(STORAGE_FORMAT);
        }
        return "";
    }
}

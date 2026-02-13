package tasks;

/**
 * Represents a task with a title and completion status.
 */
public abstract class Task {
    String title;
    boolean isDone;

    /**
     * Creates a new task with the given title.
     *
     * @param title The title of the task.
     */
    public Task(String title) {
        // Precondition: title should not be null or empty
        assert title != null && !title.trim().isEmpty() : "Task title should not be null or empty";
        this.title = title;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + title;
    }
}

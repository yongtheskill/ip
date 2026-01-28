package tasks;

/**
 * Represents a simple todo task without a deadline.
 */
public class Todo extends Task {
    public Todo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

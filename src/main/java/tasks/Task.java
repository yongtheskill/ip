package tasks;

public class Task {
    String title;

    public Task(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}

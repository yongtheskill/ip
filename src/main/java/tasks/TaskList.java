package tasks;

import java.util.ArrayList;
import exceptions.InvalidParameterException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task[] getTasks() {
        return this.tasks.toArray(new Task[0]);
    }

    public Task getTask(int index) throws InvalidParameterException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidParameterException(
                    "Task number is out of range! Please enter a valid task number (1 to " + this.tasks.size() + ").");
        }
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void deleteTask(int index) throws InvalidParameterException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidParameterException(
                    "Task number is out of range! Please enter a valid task number (1 to " + this.tasks.size() + ").");
        }
        this.tasks.remove(index);
    }
}

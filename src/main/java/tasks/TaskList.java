package tasks;

import java.util.ArrayList;
import exceptions.InvalidParameterException;
import storage.Storage;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a new empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list and saves it to storage.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        save();
    }

    public void addTaskWithoutSaving(Task task) {
        this.tasks.add(task);
    }

    public Task[] getTasks() {
        return this.tasks.toArray(new Task[0]);
    }

    /**
     * Gets a task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws InvalidParameterException If the index is out of range.
     */
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
        save();
    }

    public Task[] findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        for (Task task : this.tasks) {
            if (task.getTitle().toLowerCase().contains(lowerKeyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks.toArray(new Task[0]);
    }

    public void save() {
        Storage.save(this);
    }
}

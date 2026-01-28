package tasks;

import java.util.ArrayList;
import exceptions.InvalidParameterException;
import storage.Storage;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

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

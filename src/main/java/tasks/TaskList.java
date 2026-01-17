package tasks;

import exceptions.InvalidParameterException;

public class TaskList {
    private Task[] tasks;
    private int size;

    public TaskList() {
        this.tasks = new Task[100];
        this.size = 0;
    }

    public void addTask(Task task) {
        this.tasks[this.size] = task;
        this.size++;
    }

    public Task[] getTasks() {
        Task[] currentTasks = new Task[this.size];
        System.arraycopy(this.tasks, 0, currentTasks, 0, this.size);
        return currentTasks;
    }

    public Task getTask(int index) throws InvalidParameterException {
        if (index < 0 || index >= this.size) {
            throw new InvalidParameterException(
                    "Task number is out of range! Please enter a valid task number (1 to " + this.size + ").");
        }
        return this.tasks[index];
    }

    public int getSize() {
        return this.size;
    }
}

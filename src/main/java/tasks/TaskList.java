package tasks;

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

    public Task getTask(int index) {
        return this.tasks[index];
    }

    public int getSize() {
        return this.size;
    }
}

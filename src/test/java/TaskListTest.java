import exceptions.InvalidParameterException;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void getTask_validIndex_returnsTask() throws InvalidParameterException {
        Task task = new Todo("Read book");
        taskList.addTaskWithoutSaving(task);

        Task retrievedTask = taskList.getTask(0);
        assertEquals(task, retrievedTask);
    }

    @Test
    public void getTask_negativeIndex_throwsInvalidParameterException() {
        Task task = new Todo("Read book");
        taskList.addTaskWithoutSaving(task);

        assertThrows(InvalidParameterException.class, () -> taskList.getTask(-1));
    }

    @Test
    public void getTask_indexEqualToSize_throwsInvalidParameterException() {
        Task task = new Todo("Read book");
        taskList.addTaskWithoutSaving(task);

        assertThrows(InvalidParameterException.class, () -> taskList.getTask(1));
    }

    @Test
    public void getTask_indexGreaterThanSize_throwsInvalidParameterException() {
        Task task = new Todo("Read book");
        taskList.addTaskWithoutSaving(task);

        assertThrows(InvalidParameterException.class, () -> taskList.getTask(5));
    }

    @Test
    public void getTask_emptyList_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> taskList.getTask(0));
    }

    @Test
    public void getTask_multipleTasks_returnsCorrectTask() throws InvalidParameterException {
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        Task task3 = new Todo("Task 3");
        taskList.addTaskWithoutSaving(task1);
        taskList.addTaskWithoutSaving(task2);
        taskList.addTaskWithoutSaving(task3);

        assertEquals(task1, taskList.getTask(0));
        assertEquals(task2, taskList.getTask(1));
        assertEquals(task3, taskList.getTask(2));
    }

    @Test
    public void deleteTask_validIndex_removesTask() throws InvalidParameterException {
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        taskList.addTaskWithoutSaving(task1);
        taskList.addTaskWithoutSaving(task2);

        taskList.deleteTask(0);

        assertEquals(1, taskList.getSize());
        assertEquals(task2, taskList.getTask(0));
    }

    @Test
    public void deleteTask_negativeIndex_throwsInvalidParameterException() {
        Task task = new Todo("Read book");
        taskList.addTaskWithoutSaving(task);

        assertThrows(InvalidParameterException.class, () -> taskList.deleteTask(-1));
    }

    @Test
    public void deleteTask_indexEqualToSize_throwsInvalidParameterException() {
        Task task = new Todo("Read book");
        taskList.addTaskWithoutSaving(task);

        assertThrows(InvalidParameterException.class, () -> taskList.deleteTask(1));
    }

    @Test
    public void deleteTask_indexGreaterThanSize_throwsInvalidParameterException() {
        Task task = new Todo("Read book");
        taskList.addTaskWithoutSaving(task);

        assertThrows(InvalidParameterException.class, () -> taskList.deleteTask(10));
    }

    @Test
    public void deleteTask_emptyList_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> taskList.deleteTask(0));
    }

    @Test
    public void deleteTask_lastTask_updatesListCorrectly() throws InvalidParameterException {
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        Task task3 = new Todo("Task 3");
        taskList.addTaskWithoutSaving(task1);
        taskList.addTaskWithoutSaving(task2);
        taskList.addTaskWithoutSaving(task3);

        taskList.deleteTask(2);

        assertEquals(2, taskList.getSize());
        assertEquals(task1, taskList.getTask(0));
        assertEquals(task2, taskList.getTask(1));
    }

    @Test
    public void deleteTask_middleTask_updatesListCorrectly() throws InvalidParameterException {
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        Task task3 = new Todo("Task 3");
        taskList.addTaskWithoutSaving(task1);
        taskList.addTaskWithoutSaving(task2);
        taskList.addTaskWithoutSaving(task3);

        taskList.deleteTask(1);

        assertEquals(2, taskList.getSize());
        assertEquals(task1, taskList.getTask(0));
        assertEquals(task3, taskList.getTask(1));
    }

    @Test
    public void deleteTask_consecutiveDeletes_updatesListCorrectly() throws InvalidParameterException {
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        Task task3 = new Todo("Task 3");
        taskList.addTaskWithoutSaving(task1);
        taskList.addTaskWithoutSaving(task2);
        taskList.addTaskWithoutSaving(task3);

        taskList.deleteTask(0);
        taskList.deleteTask(0);

        assertEquals(1, taskList.getSize());
        assertEquals(task3, taskList.getTask(0));
    }

    @Test
    public void addTaskWithoutSaving_addsTaskToList() {
        Task task = new Todo("Test task");
        taskList.addTaskWithoutSaving(task);

        assertEquals(1, taskList.getSize());
    }

    @Test
    public void getSize_emptyList_returnsZero() {
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void getSize_afterAddingTasks_returnsCorrectSize() {
        taskList.addTaskWithoutSaving(new Todo("Task 1"));
        taskList.addTaskWithoutSaving(new Todo("Task 2"));
        taskList.addTaskWithoutSaving(new Todo("Task 3"));

        assertEquals(3, taskList.getSize());
    }

    @Test
    public void getTasks_emptyList_returnsEmptyArray() {
        Task[] tasks = taskList.getTasks();
        assertEquals(0, tasks.length);
    }

    @Test
    public void getTasks_withTasks_returnsAllTasks() {
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        taskList.addTaskWithoutSaving(task1);
        taskList.addTaskWithoutSaving(task2);

        Task[] tasks = taskList.getTasks();
        assertEquals(2, tasks.length);
        assertEquals(task1, tasks[0]);
        assertEquals(task2, tasks[1]);
    }
}

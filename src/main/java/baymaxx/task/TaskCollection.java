package baymaxx.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a generic task with a description and completion status.
 * This is the base class for all specific types of tasks such as TodoTask,
 * DeadlineTask, and EventTask.
 */
public class TaskCollection {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskCollection.
     */
    public TaskCollection() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskCollection initialized with a list of tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskCollection(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the collection.
     *
     * @param t the Task to add
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the collection by index.
     *
     * @param index the index of the task to remove
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Retrieves a task from the collection by index.
     *
     * @param index the index of the task to retrieve
     * @return the Task at the specified index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns all tasks in the collection.
     *
     * @return a List of all tasks
     */
    public List<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks in the collection.
     *
     * @return the size of the task collection
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Converts all tasks into a list of strings suitable for saving to a file.
     * Each task is converted using its own toSaveFormatString method.
     *
     * @return a List of strings representing the tasks for storage
     */
    public List<String> toSaveFormat() {
        return this.tasks.stream()
                .map(Task::toSaveFormatString)
                .toList();
    }
}

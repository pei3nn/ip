package baymaxx.task;

import java.util.ArrayList;
import java.util.List;

public class TaskCollection {
    private List<Task> tasks;

    public TaskCollection() {
        this.tasks = new ArrayList<>();
    }

    public TaskCollection(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public List<Task> getAllTasks() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Finds all tasks whose descriptions contain the given keyword.
     *
     * @param keyword the keyword to search for
     * @return a list of matching tasks
     */
    public List<Task> findTasks(String keyword) {
        return tasks.stream()
                .filter(task -> task.description.contains(keyword))
                .toList();
    }

    public List<String> toSaveFormat() {
        return this.tasks.stream()
                .map(x -> x.toSaveFormatString())
                .toList();
    }
}

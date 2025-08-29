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

    public void addTask(Task task) {
        this.tasks.add(task);
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

    public List<String> toSaveFormat() {
        return this.tasks.stream()
                .map(Task::toSaveFormatString)
                .toList();
    }
}

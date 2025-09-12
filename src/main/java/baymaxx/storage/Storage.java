package baymaxx.storage;

import baymaxx.task.Task;
import baymaxx.task.TaskCollection;
import baymaxx.ui.Ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Handles loading and saving tasks to a file for persistence.
 * Ensures the storage file and its parent directories exist.
 */
public class Storage {

    private Path filePath;

    /**
     * Constructs a Storage object for the specified file path.
     * Ensures the file and its parent directories exist.
     * @param filePath Path to the storage file
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        ensureFileExist();

        assert this.filePath != null : "filePath should not be null";
        assert this.filePath.toFile().exists() :
                "Storage file should exist after ensureFileExist()";
    }

    /**
     * Ensures the storage file and its parent directories exist.
     * Creates them if they do not exist.
     */
    public void ensureFileExist() {
        try {
            File file = filePath.toFile();
            File parentDir = file.getParentFile();

            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            Ui.printCreateFileError(e);
        }
    }

    /**
     * Loads tasks from the storage file.
     * Each line is converted to a Task and added to a TaskCollection.
     * @return TaskCollection containing all loaded tasks
     * @throws IOException If an I/O error occurs during reading
     */
    public TaskCollection loadTasks() throws IOException {
        TaskCollection taskList = new TaskCollection();
        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            Task task = Task.fromSaveFormat(line);
            taskList.addTask(task);
        }
        return taskList;
    }

    /**
     * Saves all tasks in the given TaskCollection to the storage file.
     * @param tasks The TaskCollection to save
     */
    public void saveTasks(TaskCollection tasks) {
        try {
            Files.write(filePath, tasks.toSaveFormat());
        } catch (IOException e) {
            Ui.printErrorSavingTask(e);
        }
    }
}

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
 * Handles loading and saving tasks to a file.
 */
public class Storage {

    private Path filePath;

    /**
     * Constructs a Storage object with the specified file path.
     * Ensures the file and its parent directory exist.
     * @param filePath the path to the storage file
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        ensureFileExist();
    }

    private void ensureFileExist() {
        try {
            File file = filePath.toFile();
            createParentDirectoryIfNeeded(file);
            createFileIfNeeded(file);
        } catch (IOException e) {
            Ui.printCreateFileError(e);
        }
    }

    private void createParentDirectoryIfNeeded(File file) {
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    private void createFileIfNeeded(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Loads tasks from the storage file.
     * @return a TaskCollection containing all loaded tasks
     * @throws IOException if an I/O error occurs reading from the file
     */
    public TaskCollection loadTasks() throws IOException {
        List<String> lines = readLinesFromFile();
        return parseTasks(lines);
    }

    private List<String> readLinesFromFile() throws IOException {
        return Files.readAllLines(filePath);
    }

    private TaskCollection parseTasks(List<String> lines) throws IOException {
        TaskCollection taskList = new TaskCollection();
        for (String line : lines) {
            Task task = Task.fromSaveFormat(line);
            taskList.addTask(task);
        }
        return taskList;
    }

    /**
     * Saves the given tasks to the storage file.
     * @param tasks the TaskCollection to save
     */
    public void saveTasks(TaskCollection tasks) {
        try {
            Files.write(filePath, tasks.toSaveFormat());
        } catch (IOException e) {
            Ui.printErrorSavingTask(e);
        }
    }
}
